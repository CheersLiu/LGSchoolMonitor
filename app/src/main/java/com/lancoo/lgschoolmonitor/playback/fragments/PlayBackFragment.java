package com.lancoo.lgschoolmonitor.playback.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancoo.cpbase.authentication.base.CurrentUser;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.base.BaseFragment;
import com.lancoo.lgschoolmonitor.base.BaseMonitorActivity;
import com.lancoo.lgschoolmonitor.base.Constant;
import com.lancoo.lgschoolmonitor.base.Global;
import com.lancoo.lgschoolmonitor.playback.activities.PlayBackAreaActivity;
import com.lancoo.lgschoolmonitor.playback.adapter.PlayBackListAdapter;
import com.lancoo.lgschoolmonitor.playback.api.InitLoader;
import com.lancoo.lgschoolmonitor.playback.bean.BuildingCameraBean;
import com.lancoo.lgschoolmonitor.playback.bean.CameraBean;
import com.lancoo.lgschoolmonitor.playback.bean.ClassroomCameraXMLBean;
import com.lancoo.lgschoolmonitor.playback.bean.OuterBuildCameraBean;
import com.lancoo.lgschoolmonitor.utils.RetrofitServiceManager;
import com.lancoo.lgschoolmonitor.utils.ToastUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;


/**
 * @author Hinata-Liu
 * @date 2018/3/5 19:04.
 */
public class PlayBackFragment extends BaseFragment {

    private BaseMonitorActivity activity;
    RecyclerView mRecListView;
    private PlayBackListAdapter mAdapter;
    private ArrayList<BuildingCameraBean> mBuildData;
    private ArrayList<CameraBean> mCamData;
    private DbUtils dbUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseMonitorActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.playback_fragment_layout, container,
                false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        mRecListView = view.findViewById(R.id.playbackRecView);
        mBuildData = new ArrayList<>();
        mCamData = new ArrayList<>();
        dbUtils = DbUtils.create(activity, Constant.DB_NAME
                + CurrentUser.UserID + ".db");
        mAdapter = new PlayBackListAdapter(activity, mBuildData);
        GridLayoutManager gridManager = new GridLayoutManager(activity, 2);
        mRecListView.setLayoutManager(gridManager);
        mRecListView.setItemAnimator(new DefaultItemAnimator());
        mRecListView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new PlayBackListAdapter.OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("AreaName", mBuildData.get(position).getBuildingName());
                intent.putExtra("AreaID", mBuildData.get(position).getBuildingId());
                intent.putExtra("AreaType", mBuildData.get(position).getType());
                intent.setClass(activity, PlayBackAreaActivity.class);
                startActivity(intent);
            }
        });
        getDbBuildData();
    }

    /**
     * 获取数据库中的缓存数据，如果取不到数据则访问网络或取服务端数据
     *
     * @author Hinata-Liu
     * @date 2018/5/22 8:58
     */
    private void getDbBuildData() {
        showProcessDialog(activity);
        try {
            ArrayList<BuildingCameraBean> buildDbList = (ArrayList<BuildingCameraBean>) dbUtils
                    .findAll(BuildingCameraBean.class);
            if (null != buildDbList && buildDbList.size() > 0) {
                //正常取到数据
                dismissProcessDialog();
                mBuildData.clear();
                mBuildData.addAll(buildDbList);
                mAdapter.notifyDataSetChanged();
            }else{
                //未取到数据，缓存中没有，访问网络获取
                netGetClassRoomCameraData();
            }
        } catch (DbException e) {
            e.printStackTrace();
            //未取到数据，数据库操作出现异常，访问网络获取
            netGetClassRoomCameraData();
        }
    }

    /**
     * toast提示
     *
     * @param resId
     */
    private void toast(int resId) {
        ToastUtil.toast(activity, resId);
    }

    /**
     * toast提示
     *
     * @param str
     */
    private void toast(String str) {
        ToastUtil.toast(activity, str);
    }


    @SuppressLint("CheckResult")
    private void netGetClassRoomCameraData() {
        Retrofit retrofit = RetrofitServiceManager.getXmlRetrofit(Global.mInsideBaseUrl);
        InitLoader initLoader = new InitLoader(retrofit);
        initLoader.getClassroomCameraXMLBean().subscribe(new Consumer<ClassroomCameraXMLBean>() {
            @Override
            public void accept(ClassroomCameraXMLBean classroomCameraXMLBean) throws Exception {
                getCameraParse(classroomCameraXMLBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissProcessDialog();
                toast("访问网络出错了，请重新登录后再次尝试！");
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void netGetOuterBuildCameraData() {
        Retrofit retrofit = RetrofitServiceManager.getGsonRetrofit(Global.mOuterBaseUrl);
        InitLoader initLoader = new InitLoader(retrofit);
        initLoader.getOuterBuildCameraBean().subscribe(new Consumer<List<OuterBuildCameraBean>>
                () {
            @Override
            public void accept(List<OuterBuildCameraBean> outerBuildCameraBeans) throws
                    Exception {
                dismissProcessDialog();
                if (null != outerBuildCameraBeans && outerBuildCameraBeans.size() > 0) {
                    getOuterCameraPaese(outerBuildCameraBeans);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissProcessDialog();
                toast("访问网络出错了，请重新登录后再次尝试！");
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getOuterCameraPaese(List<OuterBuildCameraBean> outerBuildCameraBeans) {
        for (int i = 0; i < outerBuildCameraBeans.size(); i++) {
            BuildingCameraBean buildingCameraBean = new BuildingCameraBean();
            buildingCameraBean.setBuildingId(outerBuildCameraBeans.get(i).getAreaID());
            buildingCameraBean.setBuildingName(outerBuildCameraBeans.get(i).getAreaName());
            buildingCameraBean.setCamNum(outerBuildCameraBeans.get(i).getCameraList().size());
            buildingCameraBean.setType("OUTER");
            mBuildData.add(buildingCameraBean);
            List<OuterBuildCameraBean.CameraListBean> cameraListBeans = outerBuildCameraBeans.get
                    (i).getCameraList();
            if (null != cameraListBeans && cameraListBeans.size() > 0) {
                for (int j = 0; j < cameraListBeans.size(); j++) {
                    OuterBuildCameraBean.CameraListBean cameraListBean = cameraListBeans.get(j);
                    CameraBean cameraBean = new CameraBean();
                    cameraBean.setBuildingId(buildingCameraBean.getBuildingId());
                    cameraBean.setBuildingName(buildingCameraBean.getBuildingName());
                    cameraBean.setCamId(cameraListBean.getCameraID());
                    cameraBean.setCamName(cameraListBean.getCameraName());
                    cameraBean.setCamIP(cameraListBean.getCameraIP());
                    cameraBean.setCamPort(cameraListBean.getCameraPort());
                    cameraBean.setAccessAccount(cameraListBean.getAccessAccount());
                    cameraBean.setAccessPwd(cameraListBean.getAccessPwd());
                    cameraBean.setBrand(cameraListBean.getBrand());
                    cameraBean.setPosition(cameraListBean.getPosition());
                    cameraBean.setRecordServerIp(cameraListBean.getRecordServerID());
                    cameraBean.setBuildType("OUTER");
                    mCamData.add(cameraBean);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
        try {
            ArrayList<CameraBean> dbCameraList = (ArrayList<CameraBean>) dbUtils.findAll(CameraBean
                    .class);
            ArrayList<BuildingCameraBean> dbBuildList = (ArrayList<BuildingCameraBean>) dbUtils
                    .findAll(BuildingCameraBean.class);
            if (null != dbCameraList && dbCameraList.size() > 0) {
                dbUtils.deleteAll(dbCameraList);
            }
            if (null != dbBuildList && dbBuildList.size() > 0) {
                dbUtils.deleteAll(dbBuildList);
            }
            dbUtils.saveOrUpdateAll(mBuildData);
            dbUtils.saveOrUpdateAll(mCamData);
        } catch (DbException e) {
        }
    }


    /**
     * 将网络获取到的对象转化为自定义的CameraBean对象，用来保存数据库表
     *
     * @author Hinata-Liu
     * @date 2018/5/17 12:53
     */
    private void getCameraParse(ClassroomCameraXMLBean classroomCameraXMLBean) {
        List<ClassroomCameraXMLBean.ModelInfoRoomGetWithCameras> buildList =
                classroomCameraXMLBean.getBuildingList();
        mBuildData.clear();
        mCamData.clear();
        if (null != buildList && buildList.size() > 0) {
            for (int b = 0; b < buildList.size(); b++) {
                ClassroomCameraXMLBean.ModelInfoRoomGetWithCameras buildBean = buildList.get(b);
                BuildingCameraBean buildingCameraBean = new BuildingCameraBean();
                buildingCameraBean.setBuildingId(buildBean.getBuildingId());
                buildingCameraBean.setBuildingName(buildBean.getBuildingName());
                buildingCameraBean.setType("INSIDE");
                List<ClassroomCameraXMLBean.RoomList> roomList = buildBean.getRoomList();
                int buildCamNum = 0;
                if (null == roomList || roomList.size() <= 0) {
                    buildingCameraBean.setCamNum(buildCamNum);
                    mBuildData.add(buildingCameraBean);
                    break;
                } else {
                    for (int r = 0; r < roomList.size(); r++) {
                        List<ClassroomCameraXMLBean.RoomInfo> roomInfoList = roomList.get(r)
                                .getRoomInfoList();
                        if (null != roomInfoList && roomInfoList.size() > 0) {
                            for (int i = 0; i < roomInfoList.size(); i++) {
                                ClassroomCameraXMLBean.RoomInfo roomInfo = roomInfoList.get(i);
                                List<ClassroomCameraXMLBean.Model_CameraInfo> cameraInfoList =
                                        roomInfo.getCameras().getModelCameraInfoList();
                                for (int c = 0; c < cameraInfoList.size(); c++) {
                                    buildCamNum++;
                                    CameraBean cameraBean = new CameraBean();
                                    ClassroomCameraXMLBean.Model_CameraInfo cameraInfo =
                                            cameraInfoList.get(c);
                                    cameraBean.setBuildingId(buildBean.getBuildingId());
                                    cameraBean.setBuildingName(buildBean.getBuildingName());
                                    cameraBean.setRoomId(roomInfo.getRoomId());
                                    cameraBean.setRoomName(roomInfo.getRoomName());
                                    cameraBean.setRecordServerIp(roomInfo.getRecordServerIp());
                                    cameraBean.setCamId(cameraInfo.getCamId());
                                    cameraBean.setCamName(cameraInfo.getCamName());
                                    cameraBean.setCamType(cameraInfo.getCamType());
                                    cameraBean.setCamInfo(cameraInfo.getCamInfo());
                                    cameraBean.setResolutionWidth(cameraInfo.getResolutionWidth
                                            ());
                                    cameraBean.setResolutionHeight(cameraInfo.getResolutionHeight
                                            ());
                                    cameraBean.setErrorFlag(cameraInfo.getErrorFlag());
                                    cameraBean.setBuildType("INSIDE");
                                    buildingCameraBean.setCamNum(buildCamNum);
                                    mCamData.add(cameraBean);
                                }
                            }
                        }
                    }
                }
                mBuildData.add(buildingCameraBean);
            }
        }
        netGetOuterBuildCameraData();
    }

}

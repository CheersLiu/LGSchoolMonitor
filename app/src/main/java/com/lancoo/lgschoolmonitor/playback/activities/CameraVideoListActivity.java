package com.lancoo.lgschoolmonitor.playback.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lancoo.cpbase.authentication.base.CurrentUser;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.base.BaseActivity;
import com.lancoo.lgschoolmonitor.base.Constant;
import com.lancoo.lgschoolmonitor.base.Global;
import com.lancoo.lgschoolmonitor.playback.adapter.CameraVideoListAdapter;
import com.lancoo.lgschoolmonitor.playback.api.InitLoader;
import com.lancoo.lgschoolmonitor.playback.bean.CameraBean;
import com.lancoo.lgschoolmonitor.playback.bean.CameraVideoBean;
import com.lancoo.lgschoolmonitor.playback.util.VideoDownloadObservable;
import com.lancoo.lgschoolmonitor.utils.GsonUtil;
import com.lancoo.lgschoolmonitor.utils.RetrofitServiceManager;
import com.lancoo.lgschoolmonitor.utils.StringUtil;
import com.lancoo.lgschoolmonitor.view.AutoBgImageView;
import com.lidroid.xutils.DbUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/21 8:45.
 */
public class CameraVideoListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.dateText)
    TextView mDateText;
    @BindView(R.id.cameraVideoRecView)
    RecyclerView mCamVidRecView;
    private Unbinder mUnbinder;
    private CameraBean mDataCam;
    private ArrayList<CameraVideoBean.VideoBean> mDataList;
    private CameraVideoListAdapter mAdapter;
    private DatePicker mDatePicker;
    private Calendar mTime;
    private AlertDialog mDatePickerDialog;
    private VideoDownloadObservable mObservable;
    private DbUtils mDbutil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_videolist_activity_layout);
        initToolBar(R.layout.playback_area_activity_toolbar_layout);
        mUnbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        mDataCam = getIntent().getParcelableExtra("CameraBean");
        if (null == mDataCam) {
            toast("未获取到摄像头信息，请重新选择！");
            finish();
            return;
        }
        initActionBar();
        mTime = Calendar.getInstance(Locale.CHINA);
        mDateText.setText(currentTimeToTimeStr("yyyy-MM-dd"));
        mDateText.setOnClickListener(this);
        mDbutil = DbUtils.create(this, Constant.DB_NAME
                + CurrentUser.UserID + ".db");
        mObservable = VideoDownloadObservable.getObservable();
        mObservable.setContext(this, mDbutil);
        mDataList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mCamVidRecView.setItemAnimator(new DefaultItemAnimator());
        mCamVidRecView.setLayoutManager(manager);
        mAdapter = new CameraVideoListAdapter(this, mDataList, mObservable);
        mAdapter.setOnItemClickLitener(new OnClickVideoListener());
        mCamVidRecView.setAdapter(mAdapter);
        netGetCameraVideoData(currentTimeToTimeStr("yyyy-MM-dd"));
    }

    private void initActionBar() {
        AutoBgImageView searchIcon = findViewById(R.id.searchIcon);
        searchIcon.setVisibility(View.INVISIBLE);
        AutoBgImageView backIcon = findViewById(R.id.backIcon);
        TextView titleText = findViewById(R.id.titleText);
        StringBuilder titleName = new StringBuilder();
        if ("INSIDE".equals(mDataCam.getBuildType())) {
            if (null != mDataCam.getBuildingName() && !TextUtils.isEmpty(mDataCam.getBuildingName
                    ())) {
                titleName.append(mDataCam.getBuildingName());
            }
            if (null != mDataCam.getRoomName() && !TextUtils.isEmpty(mDataCam.getRoomName())) {
                titleName.append(mDataCam.getRoomName());
            }
            if (null != mDataCam.getCamName() && !TextUtils.isEmpty(mDataCam.getCamName())) {
                titleName.append(mDataCam.getCamName());
            }
            titleName.append("摄像头录像");
        } else {
            if (null != mDataCam.getPosition() && !TextUtils.isEmpty(mDataCam.getPosition())) {
                titleName.append(mDataCam.getPosition());
            }
            if (null != mDataCam.getCamName() && !TextUtils.isEmpty(mDataCam.getCamName())) {
                titleName.append(mDataCam.getCamName());
            }
            titleName.append("摄像头录像");
        }
        titleText.setText(titleName.toString());
        backIcon.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.backIcon:
                finish();
                break;
            case R.id.dateText:
                showDatePickerDialog();
                break;
            default:

                break;
        }
    }

    /**
     * 获取指定摄像头录像列表数据
     *
     * @author Hinata-Liu
     * @date 2018/5/21 11:43
     */
    @SuppressLint("CheckResult")
    private void netGetCameraVideoData(String dateString) {
        showProcessDialog(this);
        Retrofit retrofit = RetrofitServiceManager.getRetrofit(Global.mVideoBaseUrl);
        InitLoader initLoader = new InitLoader(retrofit);
        StringBuilder params = new StringBuilder();
        params.append(mDataCam.getCamId());
        params.append("|");
        params.append(dateString);
        initLoader.getCameraVideoString(params.toString()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                dismissProcessDialog();
                CameraVideoBean cameraVideoBean = GsonUtil.jsonToObject(Uri.decode(s),
                        CameraVideoBean.class);
                if (null != cameraVideoBean) {
                    int errorCode = StringUtil.safeStringToInt(cameraVideoBean.getError());
                    if (errorCode == 0) {// 正常获取数据
                        try {
                            ArrayList<CameraVideoBean.VideoBean> videoBeanArrayList = (ArrayList
                                    <CameraVideoBean.VideoBean>) cameraVideoBean.getData();
                            if (videoBeanArrayList.size() > 0) {
                                mDataList.clear();
                                mDataList.addAll(videoBeanArrayList);
                                mAdapter.notifyDataSetChanged();
                            } else {
                                toast("暂无录像视频信息");
                                mDataList.clear();
                                mAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            toast("暂无录像视频信息");
                            mDataList.clear();
                            mAdapter.notifyDataSetChanged();
                        }
                    } else if (errorCode == 1) {// 参数错误
                        toast("访问网络参数错误");
                    } else if (errorCode == 2) {// 函数不存在
                        toast("访问网络函数不存在");
                    } else {
                        toast("访问网络出错了");

                    }
                } else {
                    toast("获取摄像头录像列表数据失败！");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissProcessDialog();
            }
        });
    }

    /**
     * 弹出日期选择对话框
     *
     * @author Hinata-Liu
     * @date 2018/5/21 16:58
     */
    private void showDatePickerDialog() {
        LinearLayout dateTimeLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout
                .camera_video_list_datepicker_layout, null);
        mDatePicker = dateTimeLayout.findViewById(R.id.DatePicker);

        DatePicker.OnDateChangedListener dateListener = new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                mTime.set(Calendar.YEAR, year);
                mTime.set(Calendar.MONTH, monthOfYear);
                mTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };
        mDatePicker.init(mTime.get(Calendar.YEAR), mTime.get(Calendar.MONTH), mTime.get(Calendar
                .DAY_OF_MONTH), dateListener);
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        mDatePickerDialog = new AlertDialog.Builder(this).setTitle("设置录像日期").setView
                (dateTimeLayout)

                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTime.set(Calendar.YEAR, mDatePicker.getYear());
                        mTime.set(Calendar.MONTH, mDatePicker.getMonth());
                        mTime.set(Calendar.DAY_OF_MONTH, mDatePicker.getDayOfMonth());
                        String selectDate = format.format(mTime.getTime());
                        mDateText.setText(selectDate);
                        dialog.dismiss();
                        netGetCameraVideoData(selectDate);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private class OnClickVideoListener implements CameraVideoListAdapter.OnItemClickLitener {

        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent();
            intent.setClass(CameraVideoListActivity.this, PlayVideoActivity.class);
            intent.putExtra("VideoBean", mDataList.get(position));
            intent.putExtra("CameraBean", mDataCam);
            startActivity(intent);
//            CameraVideoBean.VideoBean bean = mDataList.get(position);
//            VideoDownloadBean downloadBean = new VideoDownloadBean();
//            downloadBean.setDownloadType(0);
//            downloadBean.setCurrentFileSize(0);
//            downloadBean.setUserID(CurrentUser.UserID);
//            if ("INSIDE".equals(mDataCam.getBuildType())) {
//                downloadBean.setPositionName(mDataCam.getRoomName() + mDataCam.getCamName());
//            } else {
//                downloadBean.setPositionName(mDataCam.getPosition() + mDataCam.getCamName());
//            }
//            downloadBean.setFileSize(bean.getFileSize());
//            downloadBean.setFileName(bean.getFileName());
//            downloadBean.setBuildName(mDataCam.getBuildingName());
//            downloadBean.setFtpUrl(bean.getFtpUrl());
//            downloadBean.setHttpUrl(bean.getHttpUrl());
//            downloadBean.setFileID(bean.getFileID());
//            downloadBean.setStartTime(bean.getStartTime());
//            downloadBean.setEndTime(bean.getEndTime());
//            downloadBean.setClassHourNO(bean.getClassHourNO());
//            downloadBean.setClassHourName(bean.getClassHourName());
//            downloadBean.setFileLocalPath(Constant.VIDEO_PATH + bean.getFileName());
//            mObservable.downloadVideoFromServer(downloadBean);
//            try {
//                mDbutil.saveOrUpdate(downloadBean);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }
}

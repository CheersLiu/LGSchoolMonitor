package com.lancoo.lgschoolmonitor.playback.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lancoo.cpbase.authentication.base.CurrentUser;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.base.BaseActivity;
import com.lancoo.lgschoolmonitor.base.Constant;
import com.lancoo.lgschoolmonitor.playback.adapter.PlayBackAreaAdapter;
import com.lancoo.lgschoolmonitor.playback.adapter.RoomCameraListAdapter;
import com.lancoo.lgschoolmonitor.playback.bean.CameraBean;
import com.lancoo.lgschoolmonitor.playback.bean.DataTree;
import com.lancoo.lgschoolmonitor.view.AutoBgImageView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/15 19:37.
 */
public class PlayBackAreaActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecView;
    private PlayBackAreaAdapter mAdapter;
    private RoomCameraListAdapter mRoomAdapter;
    private TextView titleText;
    private ArrayList<CameraBean> mCameraList;
    private String mAreaName;
    private String mAreaID;
    private String mAreaType;
    private DbUtils dbUtils;
    private List<DataTree<String, CameraBean>> dts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playback_area_activity_layout);
        initToolBar(R.layout.playback_area_activity_toolbar_layout);
        findView();
        init();
    }


    private void findView() {
        mRecView = findViewById(R.id.areaRecView);
    }

    private void init() {
        Intent intent = getIntent();
        mAreaName = intent.getStringExtra("AreaName");
        mAreaID = intent.getStringExtra("AreaID");
        mAreaType = intent.getStringExtra("AreaType");
        initActionBar();
        mCameraList = new ArrayList<>();
        dts = new ArrayList<>();
        dbUtils = DbUtils.create(this, Constant.DB_NAME
                + CurrentUser.UserID + ".db");
        mAdapter = new PlayBackAreaAdapter(this, mCameraList);
        mRoomAdapter = new RoomCameraListAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecView.setLayoutManager(manager);
        mRecView.setItemAnimator(new DefaultItemAnimator());
        if ("INSIDE".equals(mAreaType)) {
            mRecView.setAdapter(mRoomAdapter);
            getDbRoomData();
        } else {
            mRecView.setAdapter(mAdapter);
            getDbData();
        }
        mAdapter.setOnItemClickLitener(new PlayBackAreaAdapter.OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {
                toast(mCameraList.get(position).getCamName());
            }
        });

    }

    private void getDbData() {
        showProcessDialog(this);
        try {
            List<CameraBean> list = dbUtils.findAll(Selector
                    .from(CameraBean.class)
                    .where("buildingId", "=",
                            mAreaID).and("buildingName", "=", mAreaName).and("buildType", "=",
                            mAreaType));
            if (null != list && list.size() > 0) {
                mCameraList.clear();
                mCameraList.addAll(list);
                dismissProcessDialog();
                mAdapter.notifyDataSetChanged();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void getDbRoomData() {
        try {
            List<CameraBean> list = dbUtils.findAll(Selector
                    .from(CameraBean.class)
                    .where("buildingId", "=",
                            mAreaID).and("buildingName", "=", mAreaName).and("buildType", "=",
                            mAreaType));
            list = list.subList(0, 6);
            if (null != list && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    ArrayList<CameraBean> camList = new ArrayList<>();
                    CameraBean cameraBean = list.get(i);
                    for (int j = i; j < list.size(); j++) {
                        CameraBean bean = list.get(j);
                        if (camList.size() == 0 || bean.getRoomId().equals(camList.get(0)
                                .getRoomId())) {
                            camList.add(bean);
                            continue;
                        }
                        i = j - 1;
                        break;
                    }
                    if (dts.size() > 0 && dts.get(dts.size() - 1).getSubItems().get(dts.get(dts
                            .size() - 1)
                            .getSubItems().size() - 1).getRoomId().equals(cameraBean.getRoomId()))
                        break;
                    DataTree<String, CameraBean> dataTree = new DataTree<>(cameraBean
                            .getRoomName(), camList);
                    dts.add(dataTree);
                }
                if (null != dts && dts.size() > 0) {
                    mRoomAdapter.setData(dts);
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    private void initActionBar() {
        AutoBgImageView searchIcon = findViewById(R.id.searchIcon);
        AutoBgImageView backIcon = findViewById(R.id.backIcon);
        titleText = findViewById(R.id.titleText);
        titleText.setText(mAreaName);
        searchIcon.setOnClickListener(this);
        backIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.backIcon:
                finish();
                break;
            case R.id.searchIcon:
                Intent intent = new Intent();
                intent.setClass(this, PlayBackSearchActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }
}

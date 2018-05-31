package com.lancoo.lgschoolmonitor.playback.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lancoo.cpbase.authentication.base.CurrentUser;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.base.BaseActivity;
import com.lancoo.lgschoolmonitor.base.Constant;
import com.lancoo.lgschoolmonitor.playback.bean.CameraBean;
import com.lancoo.lgschoolmonitor.playback.bean.CameraVideoBean;
import com.lancoo.lgschoolmonitor.playback.bean.VideoDownloadBean;
import com.lancoo.lgschoolmonitor.playback.util.VideoDownloadObservable;
import com.lancoo.lgschoolmonitor.utils.SysFileUtil;
import com.lancoo.lgschoolmonitor.view.AutoBgImageView;
import com.lidroid.xutils.DbUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/29 14:39.
 */
public class PlayVideoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.videoSnapshotIV)
    ImageView mVideoSnapshotIV;
    @BindView(R.id.playBtn)
    AutoBgImageView mPlayBtn;
    @BindView(R.id.dateText)
    TextView mDate;
    @BindView(R.id.positionName)
    TextView mPosition;
    @BindView(R.id.downloadProgressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.probarHint)
    TextView mHint;
    @BindView(R.id.downloadLayout)
    FrameLayout mDownloadLayout;
    private Unbinder mUnbinder;
    private CameraVideoBean.VideoBean mVideoBean;
    private CameraBean mDataCam;
    private DownloadObserver mObserver;
    private VideoDownloadObservable mObservable;
    private DbUtils mDbutil;
    private ArrayList<VideoDownloadBean> mDownloadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video_activity_layout);
        initToolBar(R.layout.playback_area_activity_toolbar_layout);
        mUnbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        mVideoBean = intent.getParcelableExtra("VideoBean");
        mDataCam = intent.getParcelableExtra("CameraBean");
        if (null == mDataCam || null == mVideoBean) {
            toast("未获取到摄像头信息，请重新选择！");
            finish();
            return;
        }
        initActionBar();
        initLayoutData();
        mDbutil = DbUtils.create(this, Constant.DB_NAME
                + CurrentUser.UserID + ".db");
        mObservable = VideoDownloadObservable.getObservable();
        mObservable.setContext(this, mDbutil);
        mDownloadList = mObservable.getmDownList();
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
            if (null != mDataCam.getCamName() && !TextUtils.isEmpty(mDataCam.getCamName())) {
                titleName.append(mDataCam.getCamName());
            }
            titleName.append("摄像头录像");
        } else {
            if (null != mDataCam.getBuildingName() && !TextUtils.isEmpty(mDataCam.getBuildingName
                    ())) {
                titleName.append(mDataCam.getBuildingName());
            }
            if (null != mDataCam.getCamName() && !TextUtils.isEmpty(mDataCam.getCamName())) {
                titleName.append(mDataCam.getCamName());
            }
            titleName.append("摄像头录像");
        }
        titleText.setText(titleName);
        backIcon.setOnClickListener(this);

    }

    private void initLayoutData() {
        String date = mVideoBean.getStartTime() + "~" + getNoDateTimeString(mVideoBean.getEndTime
                ());
        mDate.setText(date);
        if ("INSIDE".equals(mDataCam.getBuildType())) {
            if (null != mDataCam.getRoomName() && !TextUtils.isEmpty(mDataCam.getRoomName())) {
                mPosition.setText(mDataCam.getRoomName());
            } else {
                mPosition.setText(mDataCam.getCamName());
            }
        } else {
            if (null != mDataCam.getPosition() && !TextUtils.isEmpty(mDataCam.getPosition())) {
                mPosition.setText(mDataCam.getPosition());
            } else {
                mPosition.setText(mDataCam.getCamName());
            }
        }
        try {
            File f = new File(Constant.VIDEO_PATH + mVideoBean.getFileName());
            if (f.exists()) {
                if (f.length() == mVideoBean.getFileSize()) {
                    mProgressBar.setProgress(100);
                    mHint.setText("下载已完成");
                } else {
                    if (null != mDownloadList && mDownloadList.size() > 0) {
                        VideoDownloadBean bean = null;
                        for (int i = 0; i < mDownloadList.size(); i++) {
                            if (mDownloadList.get(i).getHttpUrl().equals(mVideoBean.getHttpUrl())) {
                                bean = mDownloadList.get(i);
                                break;
                            }
                        }
                        if (null != bean) {
                            switch (bean.getDownloadType()) {
                                case 1:
                                    mProgressBar.setProgress((int) (bean.getCurrentFileSize() *
                                            100 / bean.getFileSize()));
                                    mHint.setText("已暂停");
                                    break;
                                case 2:
                                    mObserver = new DownloadObserver();
                                    mObservable.addObserver(mObserver);
                                    mHint.setText("下载中...");
                                    break;
                                case 4:
                                    mProgressBar.setProgress((int) (bean.getCurrentFileSize() *
                                            100 / bean.getFileSize()));
                                    mHint.setText("等待下载");
                                    break;
                                default:

                                    break;
                            }
                        }
                    }
                    mProgressBar.setProgress(0);
                    String hint = "下载 [" + SysFileUtil.FormetFileSize(mVideoBean.getFileSize()) +
                            "]";
                    mHint.setText(hint);
                }
            } else {
                String hint = "下载 [" + SysFileUtil.FormetFileSize(mVideoBean.getFileSize()) + "]";
                mHint.setText(hint);
            }
        } catch (Exception e) {
            String hint = "下载 [" + SysFileUtil.FormetFileSize(mVideoBean.getFileSize()) + "]";
            mHint.setText(hint);
        }
        Glide.with(this).load(getVideoSnapshotPath(mVideoBean.getHttpUrl()))
                .into(mVideoSnapshotIV);
        mPlayBtn.setOnClickListener(this);
        mDownloadLayout.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    private String getNoDateTimeString(String time) {
        String[] newTime = time.split(" ");
        if (newTime.length == 2) {
            return newTime[1];
        }
        return time;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.backIcon:
                finish();
                break;
            case R.id.playBtn:
                toast("还播放不了呀！");
                break;
            case R.id.downloadLayout:
                if (null == mObserver) {
                    VideoDownloadBean downloadBean = new VideoDownloadBean();
                    downloadBean.setDownloadType(0);
                    downloadBean.setCurrentFileSize(0);
                    downloadBean.setUserID(CurrentUser.UserID);
                    if ("INSIDE".equals(mDataCam.getBuildType())) {
                        downloadBean.setPositionName(mDataCam.getRoomName() + mDataCam.getCamName
                                ());

                    } else {
                        downloadBean.setPositionName(mDataCam.getPosition() + mDataCam.getCamName
                                ());
                    }
                    downloadBean.setFileSize(mVideoBean.getFileSize());
                    downloadBean.setFileName(mVideoBean.getFileName());
                    downloadBean.setBuildName(mDataCam.getBuildingName());
                    downloadBean.setFtpUrl(mVideoBean.getFtpUrl());
                    downloadBean.setHttpUrl(mVideoBean.getHttpUrl());
                    downloadBean.setFileID(mVideoBean.getFileID());
                    downloadBean.setStartTime(mVideoBean.getStartTime());
                    downloadBean.setEndTime(mVideoBean.getEndTime());
                    downloadBean.setClassHourNO(mVideoBean.getClassHourNO());
                    downloadBean.setClassHourName(mVideoBean.getClassHourName());
                    downloadBean.setFileLocalPath(Constant.VIDEO_PATH + mVideoBean.getFileName());
                    mObservable.downloadVideoFromServer(downloadBean);
                    try {
                        mDbutil.saveOrUpdate(downloadBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mObserver = new DownloadObserver();
                    mObservable.addObserver(mObserver);
                    mHint.setText("下载中...");
                } else {
                    toast("资源正在下载中");
                }
                break;
            default:

                break;
        }
    }

    private class DownloadObserver implements Observer {

        @Override
        public void update(Observable observable, Object data) {
            VideoDownloadBean bean = (VideoDownloadBean) data;
            int pro = (int) (bean.getCurrentFileSize() * 100 / bean.getFileSize());
            mProgressBar.setProgress(pro);
            if (pro == 100) {
                mHint.setText("下载已完成");
            }
        }
    }

    private String getVideoSnapshotPath(String path) {
        int suffx = path.lastIndexOf(".");
        String photoPath = path.substring(0, suffx) + ".jpg";
        return photoPath;
    }
}

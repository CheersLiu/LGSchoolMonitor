package com.lancoo.lgschoolmonitor.playback.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lancoo.cpbase.authentication.base.CurrentUser;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.base.BaseActivity;
import com.lancoo.lgschoolmonitor.base.Constant;
import com.lancoo.lgschoolmonitor.playback.adapter.VideoDownloadAdapter;
import com.lancoo.lgschoolmonitor.playback.bean.VideoDownloadBean;
import com.lancoo.lgschoolmonitor.playback.util.VideoDownloadObservable;
import com.lancoo.lgschoolmonitor.utils.WindowUtil;
import com.lancoo.lgschoolmonitor.view.AutoBgImageView;
import com.lidroid.xutils.DbUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/22 9:48.
 */
public class VieoDownloadActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.videoDownloadRecView)
    RecyclerView mDownloadRecView;
    @BindView(R.id.nodataLayout)
    RelativeLayout mNodataLayout;
    @BindView(R.id.nodataImg)
    ImageView mNodataImg;
    @BindView(R.id.nodataText)
    TextView mNodataText;
    @BindView(R.id.freashBtn)
    TextView mFreashBtn;
    private Unbinder mUnbinder;
    private ArrayList<VideoDownloadBean> mDataList;
    private VideoDownloadAdapter mAdapter;
    private VideoDownloadObservable mObservable;
    private DbUtils mDbutil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_download_activity_layout);
        initToolBar(R.layout.video_download_activity_toolbar_layout);
        mUnbinder = ButterKnife.bind(this);
        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        initActionBar();
        mDataList = new ArrayList<>();
        mDbutil = DbUtils.create(this, Constant.DB_NAME
                + CurrentUser.UserID + ".db");
        mObservable = VideoDownloadObservable.getObservable();
        mObservable.setContext(this, mDbutil);
        mDataList = mObservable.getmDownList();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mDownloadRecView.setItemAnimator(new DefaultItemAnimator());
        mDownloadRecView.setLayoutManager(manager);
        mAdapter = new VideoDownloadAdapter(this, mDataList, mObservable);
        mDownloadRecView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new OnClickVideoListener());
        mAdapter.setOnItemDeleteLitener(new OnDeleteDownloadVideoListener());
        if (mDataList.size() > 0) {
            mNodataLayout.setVisibility(View.GONE);
        } else {
            showNodataLayout();
        }
    }

    private void showNodataLayout() {
        mDataList.clear();
        mAdapter.notifyDataSetChanged();
        mNodataLayout.setVisibility(View.VISIBLE);
        mNodataImg.setImageResource(R.mipmap.nodata_image);
        mNodataText.setText("暂无下载录像数据！");
        mFreashBtn.setVisibility(View.INVISIBLE);
    }


    private void initActionBar() {
        AutoBgImageView backIcon = findViewById(R.id.backIcon);
        TextView titleTv = findViewById(R.id.titleText);
        TextView cleanTV = findViewById(R.id.cleanTV);
        titleTv.setText("录像下载列表");
        backIcon.setOnClickListener(this);
        cleanTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.backIcon:
                finish();
                break;
            case R.id.cleanTV:
                if (mDataList.size() > 0) {
                    createClearChatlogDialog(R.string.clear_download_list_hint);
                }else {
                    toast("数据都没有，要清空些啥子@~@");
                }
                break;
            default:

                break;
        }
    }


    private void createClearChatlogDialog(int msgID) {
        WindowUtil.showSysAlertDialog(this, getString(msgID), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mObservable.cleanAllVideoWithLocal(mDataList);
                mAdapter.notifyDataSetChanged();
                showNodataLayout();
            }
        });
    }

    private class OnClickVideoListener implements VideoDownloadAdapter.OnItemClickLitener {

        @Override
        public void onItemClick(View view, int position) {
            toast("你点击了" + mDataList.get(position).getBuildName());
        }
    }

    private class OnDeleteDownloadVideoListener implements VideoDownloadAdapter
            .OnItemDeleteLitener {

        @Override
        public void onItemDelete(View view, int position) {
            mObservable.deleteVideoWithLocal(mDataList.get(position));
            mAdapter.notifyItemChanged(position);
            if (mDataList.size() <= 0) {
                showNodataLayout();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}

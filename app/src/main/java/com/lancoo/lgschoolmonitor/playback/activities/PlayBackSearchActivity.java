package com.lancoo.lgschoolmonitor.playback.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lancoo.cpbase.authentication.base.CurrentUser;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.base.BaseActivity;
import com.lancoo.lgschoolmonitor.base.Constant;
import com.lancoo.lgschoolmonitor.playback.adapter.PlayBackSearchAdapter;
import com.lancoo.lgschoolmonitor.playback.bean.CameraBean;
import com.lancoo.lgschoolmonitor.view.AutoBgImageView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/18 11:52.
 */
public class PlayBackSearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.searchRecView)
    RecyclerView mRecView;
    private Unbinder mUnbinder;
    private EditText mEditText;
    private AutoBgImageView mDeleteIcon;
    private DbUtils dbUtils;
    private ArrayList<CameraBean> mDbCamList;
    private ArrayList<CameraBean> mSearchList;
    private PlayBackSearchAdapter mAdapter;
    private long mLastSearchTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playback_search_activity_layout);
        initToolBar(R.layout.playback_search_activity_toolbar_layout);
        mUnbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        initActionBar();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        dbUtils = DbUtils.create(this, Constant.DB_NAME
                + CurrentUser.UserID + ".db");
        mDbCamList = new ArrayList<>();
        mSearchList = new ArrayList<>();
        mRecView.setItemAnimator(new DefaultItemAnimator());
        mRecView.setLayoutManager(manager);
        mAdapter = new PlayBackSearchAdapter(this, mSearchList);
        mAdapter.setOnItemClickLitener(new OnClickSearchCameraListener());
        mRecView.setAdapter(mAdapter);
        getDbCameraData();
    }

    private void initActionBar() {
        AutoBgImageView backIcon = toolbar.findViewById(R.id.backIcon);
        mEditText = toolbar.findViewById(R.id.etSearch);
        mDeleteIcon = toolbar.findViewById(R.id.deleteIcon);
        TextView searchText = toolbar.findViewById(R.id.searchText);
        backIcon.setOnClickListener(this);
        mDeleteIcon.setOnClickListener(this);
        searchText.setOnClickListener(this);
        mDeleteIcon.setVisibility(View.INVISIBLE);
        mEditText.setOnEditorActionListener(new EditEditorActionListener());
        mEditText.addTextChangedListener(new EditTextChangeListener());
    }

    private void getDbCameraData() {
        try {
            ArrayList<CameraBean> dbList = (ArrayList<CameraBean>) dbUtils.findAll(CameraBean
                    .class);
            if (null != dbList && dbList.size() > 0) {
                mDbCamList.addAll(dbList);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
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
            case R.id.deleteIcon:
                mEditText.setText("");
                mSearchList.clear();
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.searchText:
                searchCameraByKeyword();
                break;
            default:

                break;
        }
    }

    private class OnClickSearchCameraListener implements PlayBackSearchAdapter.OnItemClickLitener {

        @Override
        public void onItemClick(View view, int position) {
            toast(mSearchList.get(position).getCamName());
        }
    }

    private void searchCameraByKeyword() {
        String trim = mEditText.getText().toString().trim();
        if (System.currentTimeMillis() - mLastSearchTime > 1000) {
            mLastSearchTime = System.currentTimeMillis();
        } else {
            return;
        }
        if (!TextUtils.isEmpty(trim)) {
            showProcessDialog(this);
            searchCameraData(trim);
        }
        hideKeyboard();
    }

    private void searchCameraData(String keyString) {
        mSearchList.clear();
        mAdapter.setKeyString(keyString);
        for (int i = 0; i < mDbCamList.size(); i++) {
            if (null != mDbCamList.get(i).getBuildingName() && mDbCamList.get(i)
                    .getBuildingName().toLowerCase().contains(keyString.toLowerCase())) {
                mSearchList.add(mDbCamList.get(i));
            } else if (null != mDbCamList.get(i).getRoomName() && mDbCamList.get(i)
                    .getRoomName().toLowerCase().contains(keyString.toLowerCase())) {
                mSearchList.add(mDbCamList.get(i));
            } else if (null != mDbCamList.get(i).getCamName() && mDbCamList.get(i)
                    .getCamName().toLowerCase().contains(keyString.toLowerCase())) {
                mSearchList.add(mDbCamList.get(i));
            } else if (null != mDbCamList.get(i).getPosition() && mDbCamList.get(i)
                    .getPosition().toLowerCase().contains(keyString.toLowerCase())) {
                mSearchList.add(mDbCamList.get(i));
            }
        }
        dismissProcessDialog();
        mAdapter.notifyDataSetChanged();
    }

    /**
     * @author liuhao // 点击软键盘上的搜索键监听事件
     */
    private class EditEditorActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView textView, int actionId,
                                      KeyEvent keyEvent) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchCameraByKeyword();
            }
            return true;
        }
    }

    /**
     * @author liuhao 在搜索框中输入时的监听
     */
    private class EditTextChangeListener implements TextWatcher {

        @Override
        public void afterTextChanged(Editable s) {
            String s1 = s + "";
            if (s1.length() > 0) {
                mDeleteIcon.setVisibility(View.VISIBLE);
            } else {
                mDeleteIcon.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            if (TextUtils.isEmpty(arg0.toString())) {
                mDeleteIcon.setVisibility(View.INVISIBLE);
            } else {
                mDeleteIcon.setVisibility(View.VISIBLE);
            }
        }
    }
}

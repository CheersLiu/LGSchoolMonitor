package com.lancoo.lgschoolmonitor.me.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.base.BaseFragment;
import com.lancoo.lgschoolmonitor.base.BaseMonitorActivity;


/**
 * @author Hinata-Liu
 * @date 2018/3/5 19:03.
 */

public class MeFragment extends BaseFragment {

    private BaseMonitorActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseMonitorActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment_layout, container,
                false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

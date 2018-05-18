package com.lancoo.lgschoolmonitor.playback.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lancoo.lgschoolmonitor.R;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/15 17:17.
 */
public class PlayBackListAdapterViewHolder extends RecyclerView.ViewHolder {

    public ImageView mAreaBgImage;
    public TextView mAreaName;
    public TextView mCameraNum;

    public PlayBackListAdapterViewHolder(View itemView) {
        super(itemView);
        mAreaBgImage = itemView.findViewById(R.id.areaBgImage);
        mAreaName = itemView.findViewById(R.id.areaName);
        mCameraNum = itemView.findViewById(R.id.camNum);
    }
}

package com.lancoo.lgschoolmonitor.playback.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.view.AutoBgImageView;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/21 14:05.
 */
public class CameraVideoListAdapterViewHolder extends RecyclerView.ViewHolder {

    public TextView durationTime;
    public ImageView videoSnapshot;
    public AutoBgImageView playBtn;

    public CameraVideoListAdapterViewHolder(View itemView) {
        super(itemView);
        durationTime = itemView.findViewById(R.id.durationTimeText);
        videoSnapshot = itemView.findViewById(R.id.videoSnapshotIV);
        playBtn = itemView.findViewById(R.id.playBtn);
    }
}

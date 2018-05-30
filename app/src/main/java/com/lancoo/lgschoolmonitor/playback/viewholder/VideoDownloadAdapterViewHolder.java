package com.lancoo.lgschoolmonitor.playback.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.view.AutoBgImageView;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/22 10:00.
 */
public class VideoDownloadAdapterViewHolder extends RecyclerView.ViewHolder{

    public RelativeLayout contentLayout;
    public TextView buildName;
    public TextView positionName;
    public TextView durationTime;
    public TextView deleteBtnText;
    public TextView downloadProgress;
    public ImageView videoSnapshot;
    public RelativeLayout downloadRL;
    public AutoBgImageView downloadIcon;
    public AutoBgImageView pauseIcon;

    public VideoDownloadAdapterViewHolder(View itemView) {
        super(itemView);
        contentLayout = itemView.findViewById(R.id.contentRL);
        videoSnapshot = itemView.findViewById(R.id.videoSnapshotIV);
        downloadRL = itemView.findViewById(R.id.downloadRL);
        downloadIcon = itemView.findViewById(R.id.downloadIcon);
        pauseIcon = itemView.findViewById(R.id.pauseIcon);
        downloadProgress = itemView.findViewById(R.id.downloadProText);
        buildName = itemView.findViewById(R.id.buildName);
        positionName = itemView.findViewById(R.id.positionName);
        durationTime = itemView.findViewById(R.id.durationTimeText);
        deleteBtnText = itemView.findViewById(R.id.deleteText);
    }
}

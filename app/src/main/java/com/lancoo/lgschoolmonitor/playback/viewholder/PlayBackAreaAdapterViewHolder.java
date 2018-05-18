package com.lancoo.lgschoolmonitor.playback.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lancoo.lgschoolmonitor.R;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/15 20:02.
 */
public class PlayBackAreaAdapterViewHolder extends RecyclerView.ViewHolder {

    public TextView mCamName;

    public PlayBackAreaAdapterViewHolder(View itemView) {
        super(itemView);
        mCamName = itemView.findViewById(R.id.camName);
    }
}

package com.lancoo.lgschoolmonitor.playback.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lancoo.lgschoolmonitor.R;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/18 15:12.
 */
public class PlayBackSearchAdapterViewHolder extends RecyclerView.ViewHolder {

    public TextView mCamName;

    public PlayBackSearchAdapterViewHolder(View itemView) {
        super(itemView);
        mCamName = itemView.findViewById(R.id.camName);
    }
}

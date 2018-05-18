package com.lancoo.lgschoolmonitor.playback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.playback.bean.CameraBean;
import com.lancoo.lgschoolmonitor.playback.viewholder.PlayBackAreaAdapterViewHolder;

import java.util.ArrayList;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/15 20:11.
 */
public class PlayBackAreaAdapter extends RecyclerView.Adapter<PlayBackAreaAdapterViewHolder> {

    private Context context;
    private ArrayList<CameraBean> mData;

    public PlayBackAreaAdapter(Context context, ArrayList<CameraBean> data) {
        this.context = context;
        mData = data;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private PlayBackAreaAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(PlayBackAreaAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public PlayBackAreaAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlayBackAreaAdapterViewHolder holder = new PlayBackAreaAdapterViewHolder(LayoutInflater
                .from(context).inflate(R.layout.playback_area_adapter_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final PlayBackAreaAdapterViewHolder holder, int position) {
        CameraBean bean = mData.get(position);
        if ("OUTER".equals(bean.getBuildType())) {
            holder.mCamName.setText(bean.getPosition() + bean.getCamName
                    ());
        } else {
            holder.mCamName.setText(bean.getCamName());
        }
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

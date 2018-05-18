package com.lancoo.lgschoolmonitor.playback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.playback.bean.BuildingCameraBean;
import com.lancoo.lgschoolmonitor.playback.viewholder.PlayBackListAdapterViewHolder;

import java.util.ArrayList;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/15 17:16.
 */
public class PlayBackListAdapter extends RecyclerView.Adapter<PlayBackListAdapterViewHolder> {

    private Context context;
    private ArrayList<BuildingCameraBean> mData;

    public PlayBackListAdapter(Context context, ArrayList<BuildingCameraBean> data) {
        this.context = context;
        mData = data;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public PlayBackListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlayBackListAdapterViewHolder holder = new PlayBackListAdapterViewHolder(LayoutInflater
                .from(context).inflate(R.layout.playbacklistadapterviewholder_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final PlayBackListAdapterViewHolder holder, int position) {
        //获取当前是第几个item，从而决定要用哪一个背景图
        int bgType = (position + 1) % 5;
        switch (bgType) {
            case 0:
                holder.mAreaBgImage.setImageResource(R.mipmap.playback_area_bg5);
                break;
            case 1:
                holder.mAreaBgImage.setImageResource(R.mipmap.playback_area_bg1);
                break;
            case 2:
                holder.mAreaBgImage.setImageResource(R.mipmap.playback_area_bg2);
                break;
            case 3:
                holder.mAreaBgImage.setImageResource(R.mipmap.playback_area_bg3);
                break;
            case 4:
                holder.mAreaBgImage.setImageResource(R.mipmap.playback_area_bg4);
                break;
            default:

                break;
        }
        holder.mAreaName.setText(mData.get(position).getBuildingName());
        holder.mCameraNum.setText(mData.get(position).getCamNum() + "个摄像头");
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

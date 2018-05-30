package com.lancoo.lgschoolmonitor.playback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.playback.bean.CameraVideoBean;
import com.lancoo.lgschoolmonitor.playback.util.VideoDownloadObservable;
import com.lancoo.lgschoolmonitor.playback.viewholder.CameraVideoListAdapterViewHolder;
import com.lancoo.lgschoolmonitor.utils.GlideRoundTransform;

import java.util.ArrayList;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/21 14:45.
 */
public class CameraVideoListAdapter extends RecyclerView.Adapter<CameraVideoListAdapterViewHolder> {

    private ArrayList<CameraVideoBean.VideoBean> mDataList;
    private Context mContext;
    private VideoDownloadObservable mObservable;

    public CameraVideoListAdapter(Context context, ArrayList<CameraVideoBean.VideoBean> dataList,VideoDownloadObservable observable) {
        mContext = context;
        mDataList = dataList;
        mObservable = observable;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public CameraVideoListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CameraVideoListAdapterViewHolder holder = new CameraVideoListAdapterViewHolder
                (LayoutInflater
                        .from(mContext).inflate(R.layout.camera_video_list_item_layout, parent,
                                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final CameraVideoListAdapterViewHolder holder, int position) {
        CameraVideoBean.VideoBean bean = mDataList.get(position);
        Glide.with(mContext).load(getVideoSnapshotPath(bean.getHttpUrl()))
                .transform(new GlideRoundTransform(mContext, 5))
                .into(holder.videoSnapshot);
        String duration = getNoDateTimeString(bean.getStartTime()) + " ~ " + getNoDateTimeString(bean.getEndTime());
        holder.durationTime.setText(duration);
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

    private String getNoDateTimeString(String time) {
        String[] newTime = time.split(" ");
        if (newTime.length == 2) {
            return newTime[1];
        }
        return time;
    }

    private String getVideoSnapshotPath(String path) {
        int suffx = path.lastIndexOf(".");
        String photoPath = path.substring(0, suffx) + ".jpg";
        return photoPath;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}

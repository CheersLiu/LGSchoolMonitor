package com.lancoo.lgschoolmonitor.playback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.playback.bean.VideoDownloadBean;
import com.lancoo.lgschoolmonitor.playback.util.VideoDownloadObservable;
import com.lancoo.lgschoolmonitor.playback.viewholder.VideoDownloadAdapterViewHolder;
import com.lancoo.lgschoolmonitor.utils.SysFileUtil;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/22 11:00.
 */
public class VideoDownloadAdapter extends RecyclerView.Adapter<VideoDownloadAdapterViewHolder> {

    private Context mContext;
    private ArrayList<VideoDownloadBean> mDataList;
    private OnItemClickLitener mOnItemClickLitener;
    private OnItemDeleteLitener mOnItemDeleteLitener;
    private VideoDownloadObservable mObservable;

    public VideoDownloadAdapter(Context context, ArrayList<VideoDownloadBean> dataList,
                                VideoDownloadObservable observable) {
        mContext = context;
        mDataList = dataList;
        mObservable = observable;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public interface OnItemDeleteLitener {
        void onItemDelete(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void setOnItemDeleteLitener(OnItemDeleteLitener mOnItemDeleteLitener) {
        this.mOnItemDeleteLitener = mOnItemDeleteLitener;
    }

    @Override
    public VideoDownloadAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VideoDownloadAdapterViewHolder holder = new VideoDownloadAdapterViewHolder(LayoutInflater
                .from(mContext).inflate(R.layout.video_download_recycle_view_item_layout, parent,
                        false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final VideoDownloadAdapterViewHolder holder, int position) {

        final VideoDownloadBean bean = mDataList.get(position);
        holder.buildName.setText(bean.getBuildName());
        holder.positionName.setText(bean.getPositionName());
        String duration = bean.getStartTime() + "~" + getNoDateTimeString(bean
                .getEndTime());
        holder.durationTime.setText(duration);
        String downloadpro = SysFileUtil.FormetFileSize(bean.getCurrentFileSize()) + "/" +
                SysFileUtil.FormetFileSize(bean.getFileSize());
        Glide.with(mContext)
                .load(getVideoSnapshotPath(bean.getHttpUrl()))
                .into(holder.videoSnapshot);
        switch (bean.getDownloadType()) {
            case 0:
                //初始状态
                holder.downloadRL.setVisibility(View.VISIBLE);
                holder.downloadProgress.setText(downloadpro);
                holder.downloadIcon.setVisibility(View.VISIBLE);
                holder.pauseIcon.setVisibility(View.GONE);
                break;
            case 1:
                //暂停状态
                holder.downloadRL.setVisibility(View.VISIBLE);
                holder.downloadProgress.setText(downloadpro);
                holder.downloadIcon.setVisibility(View.VISIBLE);
                holder.pauseIcon.setVisibility(View.GONE);
                break;
            case 2:
                //下载状态
                holder.downloadRL.setVisibility(View.VISIBLE);
                holder.downloadIcon.setVisibility(View.GONE);
                holder.pauseIcon.setVisibility(View.VISIBLE);
                DownloadObserver observer = new DownloadObserver(holder.downloadProgress);
                mObservable.addObserver(observer);
                break;
            case 3:
                //下载完成状态
                holder.downloadRL.setVisibility(View.GONE);
                break;
            case 4:
                //等待下载状态
                holder.downloadRL.setVisibility(View.VISIBLE);
                holder.downloadProgress.setText(downloadpro);
                holder.downloadIcon.setVisibility(View.VISIBLE);
                holder.pauseIcon.setVisibility(View.GONE);
            case 5:
                //下载失败状态
                holder.downloadRL.setVisibility(View.VISIBLE);
                holder.downloadProgress.setText(downloadpro);
                holder.downloadIcon.setVisibility(View.VISIBLE);
                holder.pauseIcon.setVisibility(View.GONE);
            default:

                break;
        }

        if (mOnItemClickLitener != null) {
            holder.contentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.contentLayout, pos);
                }
            });
        }
        if (mOnItemDeleteLitener != null) {
            holder.deleteBtnText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    mOnItemDeleteLitener.onItemDelete(holder.deleteBtnText, pos);
                }
            });
        }

        holder.downloadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击了下载按钮
                mObservable.downloadVideoFromServer(bean, 0);
//                notifyDataSetChanged();
            }
        });

        holder.pauseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击暂停按钮
                mObservable.stopDownload(bean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
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

    private class DownloadObserver implements Observer {
        private TextView mProText;

        public DownloadObserver(TextView textView) {
            mProText = textView;
        }

        @Override
        public void update(Observable observable, Object data) {
            VideoDownloadBean bean = (VideoDownloadBean) data;
            String progress = SysFileUtil.FormetFileSize(bean.getCurrentFileSize()) + "/" +
                    SysFileUtil
                            .FormetFileSize(bean.getFileSize());
            mProText.setText(progress);
            notifyDataSetChanged();
        }
    }
}

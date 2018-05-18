package com.lancoo.lgschoolmonitor.playback.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.playback.bean.CameraBean;
import com.lancoo.lgschoolmonitor.playback.viewholder.PlayBackSearchAdapterViewHolder;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/18 15:10.
 */
public class PlayBackSearchAdapter extends RecyclerView.Adapter<PlayBackSearchAdapterViewHolder> {

    private Context context;
    private ArrayList<CameraBean> mData;
    private StringBuilder keyString;

    public PlayBackSearchAdapter(Context context, ArrayList<CameraBean> data) {
        this.context = context;
        mData = data;
        keyString = new StringBuilder();
    }

    public void setKeyString(String key) {
        if (TextUtils.isEmpty(keyString.toString())) {
            keyString.append(key);
        } else {
            keyString.setLength(0);
            keyString.append(key);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public PlayBackSearchAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlayBackSearchAdapterViewHolder holder = new PlayBackSearchAdapterViewHolder(LayoutInflater
                .from(context).inflate(R.layout.playback_area_adapter_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final PlayBackSearchAdapterViewHolder holder, int position) {
        CameraBean bean = mData.get(position);
        String outerCamName = bean.getPosition() + "-" + bean.getCamName();
        String insideCamName = bean.getBuildingName() + bean.getRoomName() + "-" + bean
                .getCamName();
        if ("OUTER".equals(bean.getBuildType())) {
            try {
                holder.mCamName.setText(matcherSearchText(outerCamName));
            } catch (Exception e) {
                holder.mCamName.setText(outerCamName);
            }
        } else {
            try {
                holder.mCamName.setText(matcherSearchText(insideCamName));
            } catch (Exception e) {
                holder.mCamName.setText(outerCamName);
            }
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

    @SuppressLint("ResourceAsColor")
    private SpannableString matcherSearchText(String text) {
        String string = text.toLowerCase();
        String key = keyString.toString().toLowerCase();
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);
        SpannableString ss = new SpannableString(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(Color.rgb(0,153,255)), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

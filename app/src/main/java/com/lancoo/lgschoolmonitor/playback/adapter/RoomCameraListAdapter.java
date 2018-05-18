package com.lancoo.lgschoolmonitor.playback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.playback.bean.CameraBean;
import com.lancoo.lgschoolmonitor.playback.bean.DataTree;

import java.util.ArrayList;
import java.util.List;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/17 17:25.
 */
public class RoomCameraListAdapter extends SecondaryListAdapter<RoomCameraListAdapter
        .GroupItemViewHolder, RoomCameraListAdapter.SubItemViewHolder> {
    private Context context;

    private List<DataTree<String, CameraBean>> dts = new ArrayList<>();

    public RoomCameraListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List datas) {
        dts = datas;
        notifyNewData(dts);
    }

    @Override
    public RecyclerView.ViewHolder groupItemViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .room_camera_list_adapter_group_item, parent, false);

        return new GroupItemViewHolder(v);
    }

    @Override
    public RecyclerView.ViewHolder subItemViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .room_camera_list_adapter_sub_item, parent, false);

        return new SubItemViewHolder(v);
    }

    @Override
    public void onGroupItemBindViewHolder(RecyclerView.ViewHolder holder, int groupItemIndex) {
        ((GroupItemViewHolder) holder).roomName.setText(dts.get(groupItemIndex)
                .getGroupItem());
        ((GroupItemViewHolder) holder).expandIcon.setImageResource(R.mipmap
                .room_camera_list_group_default_icon);
    }

    @Override
    public void onSubItemBindViewHolder(RecyclerView.ViewHolder holder, int groupItemIndex, int
            subItemIndex) {

        ((SubItemViewHolder) holder).camName.setText(dts.get(groupItemIndex).getSubItems().get
                (subItemIndex).getCamName());

    }

    @Override
    public void onGroupItemClick(Boolean isExpand, GroupItemViewHolder holder, int groupItemIndex) {

        if (isExpand) {
            holder.expandIcon.setImageResource(R.mipmap
                    .room_camera_list_group_default_icon);
        } else {
            holder.expandIcon.setImageResource(R.mipmap
                    .room_camera_list_group_expand_icon);
        }

    }

    @Override
    public void onSubItemClick(SubItemViewHolder holder, int groupItemIndex, int subItemIndex) {

        Toast.makeText(context, "sub item " + String.valueOf(subItemIndex) + " in group item " +
                String.valueOf(groupItemIndex), Toast.LENGTH_SHORT).show();

    }

    public static class GroupItemViewHolder extends RecyclerView.ViewHolder {

        ImageView expandIcon;
        TextView roomName;

        public GroupItemViewHolder(View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.roomName);
            expandIcon = itemView.findViewById(R.id.expandIcon);
        }
    }

    public static class SubItemViewHolder extends RecyclerView.ViewHolder {

        TextView camName;

        public SubItemViewHolder(View itemView) {
            super(itemView);

            camName = itemView.findViewById(R.id.camName);
        }
    }

}

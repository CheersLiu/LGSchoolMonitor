package com.lancoo.lgschoolmonitor.playback.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/16 19:39.
 */
@Table
public class BuildingCameraBean implements Parcelable {


    @Id
    private int id;//数据库表中索引，自增字段
    @Column
    private String buildingId;///区域ID，对应教室外的AreaID
    @Column
    private String buildingName;///区域名称，对应教室外数据的AreaName
    @Column
    private String type;//INSIDE：表示教室内，OUTER表示教室外
    @Column
    private int camNum;//区域摄像头数目

    public BuildingCameraBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingId() {
        return buildingId == null ? "" : buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName == null ? "" : buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCamNum() {
        return camNum;
    }

    public void setCamNum(int camNum) {
        this.camNum = camNum;
    }

    public static final Parcelable.Creator<BuildingCameraBean> CREATOR = new Creator<BuildingCameraBean>() {

        @Override
        public BuildingCameraBean[] newArray(int size) {
            return new BuildingCameraBean[size];
        }

        @Override
        public BuildingCameraBean createFromParcel(Parcel source) {
            return new BuildingCameraBean(source);
        }
    };

    public BuildingCameraBean(Parcel source) {
        id = source.readInt();
        buildingId = source.readString();
        buildingName = source.readString();
        type = source.readString();
        camNum = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(buildingId);
        dest.writeString(buildingName);
        dest.writeString(type);
        dest.writeInt(camNum);
    }

    @Override
    public String toString() {
        return "BuildingCameraBean{" +
                "buildingId='" + buildingId + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", type='" + type + '\'' +
                ", camNum=" + camNum +
                '}';
    }
}

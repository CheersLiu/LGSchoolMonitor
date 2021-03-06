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
 * @date 2018/5/16 18:43.
 */
@Table
public class CameraBean implements Parcelable {

    @Id
    private int id; //数据库表中索引，自增字段
    @Column
    private String buildingId; ///区域ID，对应教室外的AreaID
    @Column
    private String buildingName; ///区域名称，对应教室外数据的AreaName
    @Column
    private String roomId;//教室ID，教室外摄像头无此属性
    @Column
    private String roomName;//教室内教室名称，教室外摄像头无此属性
    @Column
    private String camId;//教室内摄像头ID，对应教室外的CameraID
    @Column
    private String camName;//教室内摄像头名称，对应教室外的CameraName
    @Column
    private String camType;//教室内摄像头类型，教室外摄像头无此属性
    @Column
    private String camInfo;//教室内摄像头详情，教室外摄像头无此属性
    @Column
    private String resolutionWidth;//教室内摄像头像素，教室外摄像头无此属性
    @Column
    private String resolutionHeight;//教室内摄像头像素，教室外摄像头无此属性
    @Column
    private String errorFlag;//摄像头是否损坏，教室外摄像头无此属性
    @Column
    private String recordServerIp;//教室内录播服务器地址等数据，对应教室外的RecordServerID
    @Column
    private String camIP;//教室外摄像头IP，教室内摄像头无此属性
    @Column
    private String camPort;//教室外摄像头端口，教室内摄像头无此属性
    @Column
    private String accessAccount;//教室外摄像头用户名称，教室内摄像头无此属性
    @Column
    private String accessPwd;//教室外摄像头密码，教室内摄像头无此属性
    @Column
    private String brand;//教室外摄像头品牌，教室内摄像头无此属性
    @Column
    private String position;//教室外摄像头位置信息，教室内摄像头无此属性
    @Column
    private String buildType;//INSIDE：表示教室内，OUTER表示教室外

    public CameraBean() {
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

    public String getRoomId() {
        return roomId == null ? "" : roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName == null ? "" : roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCamId() {
        return camId == null ? "" : camId;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }

    public String getCamName() {
        return camName == null ? "" : camName;
    }

    public void setCamName(String camName) {
        this.camName = camName;
    }

    public String getCamType() {
        return camType == null ? "" : camType;
    }

    public void setCamType(String camType) {
        this.camType = camType;
    }

    public String getCamInfo() {
        return camInfo == null ? "" : camInfo;
    }

    public void setCamInfo(String camInfo) {
        this.camInfo = camInfo;
    }

    public String getResolutionWidth() {
        return resolutionWidth == null ? "" : resolutionWidth;
    }

    public void setResolutionWidth(String resolutionWidth) {
        this.resolutionWidth = resolutionWidth;
    }

    public String getResolutionHeight() {
        return resolutionHeight == null ? "" : resolutionHeight;
    }

    public void setResolutionHeight(String resolutionHeight) {
        this.resolutionHeight = resolutionHeight;
    }

    public String getErrorFlag() {
        return errorFlag == null ? "" : errorFlag;
    }

    public void setErrorFlag(String errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String getRecordServerIp() {
        return recordServerIp == null ? "" : recordServerIp;
    }

    public void setRecordServerIp(String recordServerIp) {
        this.recordServerIp = recordServerIp;
    }

    public String getCamIP() {
        return camIP == null ? "" : camIP;
    }

    public void setCamIP(String camIP) {
        this.camIP = camIP;
    }

    public String getCamPort() {
        return camPort == null ? "" : camPort;
    }

    public void setCamPort(String camPort) {
        this.camPort = camPort;
    }

    public String getAccessAccount() {
        return accessAccount == null ? "" : accessAccount;
    }

    public void setAccessAccount(String accessAccount) {
        this.accessAccount = accessAccount;
    }

    public String getAccessPwd() {
        return accessPwd == null ? "" : accessPwd;
    }

    public void setAccessPwd(String accessPwd) {
        this.accessPwd = accessPwd;
    }

    public String getBrand() {
        return brand == null ? "" : brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPosition() {
        return position == null ? "" : position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBuildType() {
        return buildType == null ? "" : buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }


    public static final Parcelable.Creator<CameraBean> CREATOR = new Creator<CameraBean>() {

        @Override
        public CameraBean[] newArray(int size) {
            return new CameraBean[size];
        }

        @Override
        public CameraBean createFromParcel(Parcel source) {
            return new CameraBean(source);
        }
    };

    public CameraBean(Parcel source) {
        id = source.readInt();
        buildingId = source.readString();
        buildingName = source.readString();
        roomId = source.readString();
        roomName = source.readString();
        camId = source.readString();
        camName = source.readString();
        camType = source.readString();
        camInfo = source.readString();
        resolutionWidth = source.readString();
        resolutionHeight = source.readString();
        errorFlag = source.readString();
        recordServerIp = source.readString();
        camIP = source.readString();
        camPort = source.readString();
        accessAccount = source.readString();
        accessPwd = source.readString();
        brand = source.readString();
        position = source.readString();
        buildType = source.readString();
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
        dest.writeString(roomId);
        dest.writeString(roomName);
        dest.writeString(camId);
        dest.writeString(camName);
        dest.writeString(camType);
        dest.writeString(camInfo);
        dest.writeString(resolutionWidth);
        dest.writeString(resolutionHeight);
        dest.writeString(errorFlag);
        dest.writeString(recordServerIp);
        dest.writeString(camIP);
        dest.writeString(camPort);
        dest.writeString(accessAccount);
        dest.writeString(accessPwd);
        dest.writeString(brand);
        dest.writeString(position);
        dest.writeString(buildType);
    }

    @Override
    public String toString() {
        return "CameraBean{" +
                "id=" + id +
                ", buildingId='" + buildingId + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", camId='" + camId + '\'' +
                ", camName='" + camName + '\'' +
                ", camType='" + camType + '\'' +
                ", camInfo='" + camInfo + '\'' +
                ", resolutionWidth='" + resolutionWidth + '\'' +
                ", resolutionHeight='" + resolutionHeight + '\'' +
                ", errorFlag='" + errorFlag + '\'' +
                ", recordServerIp='" + recordServerIp + '\'' +
                ", camIP='" + camIP + '\'' +
                ", camPort='" + camPort + '\'' +
                ", accessAccount='" + accessAccount + '\'' +
                ", accessPwd='" + accessPwd + '\'' +
                ", brand='" + brand + '\'' +
                ", position='" + position + '\'' +
                ", buildType='" + buildType + '\'' +
                '}';
    }
}

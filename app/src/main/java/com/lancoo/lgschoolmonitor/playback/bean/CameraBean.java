package com.lancoo.lgschoolmonitor.playback.bean;

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
public class CameraBean {
    @Id
    private int id;
    @Column
    private String buildingId;
    @Column
    private String buildingName;
    @Column
    private String roomId;
    @Column
    private String roomName;
    @Column
    private String camId;
    @Column
    private String camName;
    @Column
    private String camType;
    @Column
    private String camInfo;
    @Column
    private String resolutionWidth;
    @Column
    private String resolutionHeight;
    @Column
    private String errorFlag;
    @Column
    private String recordServerIp;
    @Column
    private String camIP;
    @Column
    private String camPort;
    @Column
    private String accessAccount;
    @Column
    private String accessPwd;
    @Column
    private String brand;
    @Column
    private String position;
    @Column
    private String buildType;

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

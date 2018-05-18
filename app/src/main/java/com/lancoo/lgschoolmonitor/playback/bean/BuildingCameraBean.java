package com.lancoo.lgschoolmonitor.playback.bean;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/16 19:39.
 */
public class BuildingCameraBean{

    private String buildingId;
    private String buildingName;
    private String type;
    private int camNum;

    public BuildingCameraBean(){

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

package com.lancoo.lgschoolmonitor.playback.bean;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/17 11:23.
 */
@Root(name = "ArrayOfModel_Info_Room_Get_WithCameras", strict = false)
@NamespaceList({@Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
        @Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
        @Namespace(reference = "http://www.chinalancoo.com/")})
public class ClassroomCameraXMLBean {
    @ElementList(entry = "Model_Info_Room_Get_WithCameras", inline = true, type =
            ModelInfoRoomGetWithCameras.class, required = false)
    List<ModelInfoRoomGetWithCameras> buildingList;

    public ClassroomCameraXMLBean() {

    }

    public ClassroomCameraXMLBean(List<ModelInfoRoomGetWithCameras> buildingList) {
        this.buildingList = buildingList;
    }

    public List<ModelInfoRoomGetWithCameras> getBuildingList() {
        if (buildingList == null) {
            return new ArrayList<>();
        }
        return buildingList;
    }

    public void setBuildingList(List<ModelInfoRoomGetWithCameras> buildingList) {
        this.buildingList = buildingList;
    }

    @Root(name = "Model_Info_Room_Get_WithCameras", strict = false)
    static public class ModelInfoRoomGetWithCameras {
        @Element(name = "buildingId", required = false)
        String buildingId;
        @Element(name = "buildingName", required = false)
        String buildingName;
        @ElementList(entry = "rooms", inline = true, type = RoomList.class, required = false)
        List<RoomList> roomList;

        public ModelInfoRoomGetWithCameras() {

        }

        public ModelInfoRoomGetWithCameras(String buildingId, String buildingName, List<RoomList> roomList) {
            this.buildingId = buildingId;
            this.buildingName = buildingName;
            this.roomList = roomList;
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

        public List<RoomList> getRoomList() {
            return roomList;
        }

        public void setRoomList(List<RoomList> roomList) {
            this.roomList = roomList;
        }
    }

    @Root(name = "rooms", strict = false)
    static public class RoomList {
        @ElementList(entry = "Info_Room_", inline = true, type = RoomInfo.class, required = false)
        List<RoomInfo> roomInfoList;

        public RoomList() {

        }

        public RoomList(List<RoomInfo> roomInfoList) {
            this.roomInfoList = roomInfoList;
        }

        public List<RoomInfo> getRoomInfoList() {
            if (roomInfoList == null) {
                return new ArrayList<>();
            }
            return roomInfoList;
        }

        public void setRoomInfoList(List<RoomInfo> roomInfoList) {
            this.roomInfoList = roomInfoList;
        }
    }

    @Root(name = "Info_Room_", strict = false)
    static public class RoomInfo {
        @Element(name = "roomId", required = false)
        String roomId;
        @Element(name = "roomName", required = false)
        String roomName;
        @Element(name = "cameras", required = false)
        Cameras cameras;
        @Element(name = "recordServerIp", required = false)
        String recordServerIp;

        public RoomInfo() {

        }

        public RoomInfo(String roomId, String roomName, Cameras cameras, String recordServerIp) {
            this.roomId = roomId;
            this.roomName = roomName;
            this.cameras = cameras;
            this.recordServerIp = recordServerIp;
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

        public Cameras getCameras() {
            return cameras;
        }

        public void setCameras(Cameras cameras) {
            this.cameras = cameras;
        }

        public String getRecordServerIp() {
            return recordServerIp == null ? "" : recordServerIp;
        }

        public void setRecordServerIp(String recordServerIp) {
            this.recordServerIp = recordServerIp;
        }
    }

    @Root(name = "cameras", strict = false)
    static public class Cameras {
        @ElementList(entry = "Model_CameraInfo", inline = true, type = Model_CameraInfo.class,
                required = false)
        List<Model_CameraInfo> modelCameraInfoList;

        public Cameras() {

        }

        public Cameras(List<Model_CameraInfo> modelCameraInfoList) {
            this.modelCameraInfoList = modelCameraInfoList;
        }

        public List<Model_CameraInfo> getModelCameraInfoList() {
            if (modelCameraInfoList == null) {
                return new ArrayList<>();
            }
            return modelCameraInfoList;
        }

        public void setModelCameraInfoList(List<Model_CameraInfo> modelCameraInfoList) {
            this.modelCameraInfoList = modelCameraInfoList;
        }
    }

    @Root(name = "Model_CameraInfo", strict = false)
    static public class Model_CameraInfo {
        @Element(name = "camId")
        String camId;
        @Element(name = "camName")
        String camName;
        @Element(name = "camType")
        String camType;
        @Element(name = "camInfo")
        String camInfo;
        @Element(name = "resolutionWidth")
        String resolutionWidth;
        @Element(name = "resolutionHeight")
        String resolutionHeight;
        @Element(name = "errorFlag")
        String errorFlag;

        public Model_CameraInfo() {

        }

        public Model_CameraInfo(String camId, String camName, String camType, String camInfo,
                                String resolutionWidth, String resolutionHeight, String errorFlag) {
            this.camId = camId;
            this.camName = camName;
            this.camType = camType;
            this.camInfo = camInfo;
            this.resolutionWidth = resolutionWidth;
            this.resolutionHeight = resolutionHeight;
            this.errorFlag = errorFlag;
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

        @Override
        public String toString() {
            return "Model_CameraInfo{" +
                    "camId='" + camId + '\'' +
                    ", camName='" + camName + '\'' +
                    ", camType='" + camType + '\'' +
                    ", camInfo='" + camInfo + '\'' +
                    ", resolutionWidth='" + resolutionWidth + '\'' +
                    ", resolutionHeight='" + resolutionHeight + '\'' +
                    ", errorFlag='" + errorFlag + '\'' +
                    '}';
        }
    }
}

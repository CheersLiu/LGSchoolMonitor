package com.lancoo.lgschoolmonitor.playback.bean;

import java.util.List;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/17 14:35.
 */
public class OuterBuildCameraBean {

    /**
     * AreaID : 1
     * AreaName : 操场区域
     * CameraList : [{"CameraID":"34254254325325","CameraName":"CAMERA01",
     * "CameraIP":"192.168.50.198","CameraPort":3338,"AccessAccount":"cameraUser",
     * "AccessPwd":"cameraPwd","Brand":2,"Position":"操作东南方","RecordServerID":""},
     * {"CameraID":"34233354325356","CameraName":"CAMERA02","CameraIP":"192.168.2.18",
     * "CameraPort":554,"AccessAccount":"admin","AccessPwd":"lg123456","Brand":1,
     * "Position":"操作东北方","RecordServerID":""},{"CameraID":"34232234325399",
     * "CameraName":"CAMERA03","CameraIP":"192.168.50.198","CameraPort":3338,
     * "AccessAccount":"cameraUser","AccessPwd":"cameraPwd","Brand":2,"Position":"操作西南方",
     * "RecordServerID":""},{"CameraID":"34232234321337","CameraName":"CAMERA04",
     * "CameraIP":"192.168.2.18","CameraPort":554,"AccessAccount":"admin","AccessPwd":"lg123456",
     * "Brand":1,"Position":"操作西北方","RecordServerID":""}]
     */

    private String AreaID;
    private String AreaName;
    private List<CameraListBean> CameraList;

    public String getAreaID() {
        return AreaID == null ? "" : AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    public String getAreaName() {
        return AreaName == null ? "" : AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public List<CameraListBean> getCameraList() {
        return CameraList;
    }

    public void setCameraList(List<CameraListBean> CameraList) {
        this.CameraList = CameraList;
    }

    public static class CameraListBean {
        /**
         * CameraID : 34254254325325
         * CameraName : CAMERA01
         * CameraIP : 192.168.50.198
         * CameraPort : 3338
         * AccessAccount : cameraUser
         * AccessPwd : cameraPwd
         * Brand : 2
         * Position : 操作东南方
         * RecordServerID :
         */

        private String CameraID;
        private String CameraName;
        private String CameraIP;
        private String CameraPort;
        private String AccessAccount;
        private String AccessPwd;
        private String Brand;
        private String Position;
        private String RecordServerID;

        public String getCameraID() {
            return CameraID == null ? "" : CameraID;
        }

        public void setCameraID(String cameraID) {
            CameraID = cameraID;
        }

        public String getCameraName() {
            return CameraName == null ? "" : CameraName;
        }

        public void setCameraName(String cameraName) {
            CameraName = cameraName;
        }

        public String getCameraIP() {
            return CameraIP == null ? "" : CameraIP;
        }

        public void setCameraIP(String cameraIP) {
            CameraIP = cameraIP;
        }

        public String getCameraPort() {
            return CameraPort == null ? "" : CameraPort;
        }

        public void setCameraPort(String cameraPort) {
            CameraPort = cameraPort;
        }

        public String getAccessAccount() {
            return AccessAccount == null ? "" : AccessAccount;
        }

        public void setAccessAccount(String accessAccount) {
            AccessAccount = accessAccount;
        }

        public String getAccessPwd() {
            return AccessPwd == null ? "" : AccessPwd;
        }

        public void setAccessPwd(String accessPwd) {
            AccessPwd = accessPwd;
        }

        public String getBrand() {
            return Brand == null ? "" : Brand;
        }

        public void setBrand(String brand) {
            Brand = brand;
        }

        public String getPosition() {
            return Position == null ? "" : Position;
        }

        public void setPosition(String position) {
            Position = position;
        }

        public String getRecordServerID() {
            return RecordServerID == null ? "" : RecordServerID;
        }

        public void setRecordServerID(String recordServerID) {
            RecordServerID = recordServerID;
        }
    }
}

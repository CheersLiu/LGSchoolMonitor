package com.lancoo.lgschoolmonitor.base;

/**
 * @author Hinata-Liu
 * @date 2018/3/6 9:15.
 */

public class Constant {

    /**
     * 访问模式 0x001 游客模式 0x002 离线模式 0x003正常模式
     */
    public static String FLAG_EXTRA_ACCESS_PATTERN = "ACCESS_PATTERN";
    /**
     * 产品的版本 1 基础版 2 完整版 5 云办公版。 其他值未正常获取到产品版本
     */
    public static int ProductType = 0;
    /**
     * 环境 1 单个专业英语院校 2 单个普通大学 3 单个中小学 4 多学校(县区范围) 5 中职学校 6 高职学校
     */
    public static int EnvironmentType=-1;
    /**
     * 访问模式，游客模式
     */
    public static int ACCESS_PATTERN_VISITOR = 0x001;
    /**
     * 访问模式离线模式
     */
    public static int ACCESS_PATTERN_OFFLINE = 0x002;
    /**
     * 访问模式正常模式
     */
    public static int ACCESS_PATTERN_NORMAL = 0x003;

    public final static String DB_NAME = "LGSchoolMonitorDB";
    // 司俊接口拼接地址
    public final static String WS_CLASSROOM_CAMERAS_SUFFIX = "WebService/Mgr_Classroom_PC.asmx";
    public final static String WS_NAME_SPACE = "http://www.chinalancoo.com/";
    // 获取教室内摄像头信息方法名
    public final static String WS_GET_ROOM_CAM_INFO_METHOD_NAME = "WS_IDMC_Room_Get_WithCameras";
}

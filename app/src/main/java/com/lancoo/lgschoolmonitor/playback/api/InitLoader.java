package com.lancoo.lgschoolmonitor.playback.api;

import com.lancoo.lgschoolmonitor.base.ObjectLoader;
import com.lancoo.lgschoolmonitor.bean.SysConfigInfoXmlBean;
import com.lancoo.lgschoolmonitor.playback.bean.ClassroomCameraXMLBean;
import com.lancoo.lgschoolmonitor.playback.bean.OuterBuildCameraBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 录像回放模块访问网络接口
 *
 * @author Hinata-Liu
 * @date 2018/5/17 11:22.
 */
public class InitLoader extends ObjectLoader {
    private ISlideServer iSlideServer;

    public InitLoader(Retrofit retrofit) {
        iSlideServer = retrofit.create(ISlideServer.class);
    }

    // 获取内外基础地址
    public Observable<SysConfigInfoXmlBean> getSysConfigInfo(String sysID, String SubjectID) {
        return observe(iSlideServer.getSysConfigInfo(sysID, SubjectID));
    }

    // 获取教室内摄像头数据
    public Observable<ClassroomCameraXMLBean> getClassroomCameraXMLBean() {
        return observe(iSlideServer.getClassroomCameraXMLBeanList());
    }

    // 获取教室外摄像头数据
    public Observable<List<OuterBuildCameraBean>> getOuterBuildCameraBean() {
        return observe(iSlideServer.getOuterBuildCameraBean());
    }


    public interface ISlideServer {
        /**
         * @params :sysID 教室外传"E00" 教室内传"M10"
         * @params :SubjectID 直接传空字符串
         */
        @GET("/Base/WS/Service_Basic.asmx/WS_G_GetSubSystemServerInfo")
        Observable<SysConfigInfoXmlBean> getSysConfigInfo(@Query("sysID") String sysID,
                                                          @Query("SubjectID") String
                                                                  SubjectID);

        /**
         * 获取教室内摄像头数据
         *
         * @return
         */
        @GET("WebService/Mgr_Classroom_PC.asmx/WS_IDMC_Room_Get_WithCameras")
        Observable<ClassroomCameraXMLBean> getClassroomCameraXMLBeanList();

        /**
         * 获取教室外摄像头数据
         *
         * @return
         */
        @GET("api/camera/getallcameras")
        Observable<List<OuterBuildCameraBean>> getOuterBuildCameraBean();

    }


}

package com.lancoo.lgschoolmonitor.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.lancoo.lgschoolmonitor.BuildConfig;
import com.lancoo.lgschoolmonitor.utils.CrashHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/16 14:47.
 */
public class LGSchoolMonitorApplication extends Application {

    private static LGSchoolMonitorApplication mInstance = null;
    public static boolean IsDubeg = true;

    private String baseAddress;
    private List<Activity> oList;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            //内存泄露库调用
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            //LeakCanary.install(this);
            //Facebook的免root调试数据库调用
            Stetho.initializeWithDefaults(this);
        }
        mInstance = this;
        oList = new ArrayList<Activity>();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public static Context getAppContext() {
        return mInstance;
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    /**
     * 添加Activity
     */
    public void addActivity(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!oList.contains(activity)) {
            oList.add(activity);// 把当前Activity添加到集合中
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity(Activity activity) {
        // 判断当前集合中存在该Activity
        if (oList.contains(activity)) {
            oList.remove(activity);// 从集合中移除
            activity.finish();// 销毁当前Activity
        }
    }

    /**
     * 销毁所有的Activity
     */

    public void removeALLActivity() {
        // 通过循环，把集合中的所有Activity销毁
        for (Activity activity : oList) {
            activity.finish();
        }
    }
}

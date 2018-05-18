package com.lancoo.lgschoolmonitor.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lancoo.lgschoolmonitor.base.LGSchoolMonitorApplication;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/16 14:42.
 */
public class NetUtil {
    /**
     * 获取手机的网络连接状态。
     *
     * @return 0（网络断开），1（wifi网络），2（2G、3G、4G网络）。
     */
    public static int getNetState() {
        ConnectivityManager conMan = (ConnectivityManager) LGSchoolMonitorApplication
                .getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMan.getActiveNetworkInfo();
        if (info != null && info.isConnected() && info.isAvailable()) { // 网络可用
            String type = info.getTypeName();
            if (type.equalsIgnoreCase("wifi"))
                return 1;
            else if (type.equalsIgnoreCase("mobile"))
                return 2;
        }
        // 网络不可用
        return 0;
    }

}

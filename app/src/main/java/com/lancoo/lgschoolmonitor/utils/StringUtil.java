package com.lancoo.lgschoolmonitor.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/21 11:54.
 */
public class StringUtil {
    private static String TAG = "StringUtils";

    public static int safeStringToInt(String text) {
        int result = Integer.MAX_VALUE;
        try {
            result = Integer.valueOf(text);
        } catch (NumberFormatException e) {
            // TODO: handle exception
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    /**
     * 当无法正常截取字符串是将返回空串"" ，
     *
     * @param content
     * @param start
     * @param end
     * @return
     */
    public static String safeSubString(String content, int start, int end) {
        String result = "";
        if (TextUtils.isEmpty(content)) {
            return result;
        }
        if (start >= 0 && start <= end && end <= content.length()) {
            result = content.substring(start, end);
        }
        return result;
    }
}

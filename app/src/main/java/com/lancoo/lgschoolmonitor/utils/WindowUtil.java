package com.lancoo.lgschoolmonitor.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.PopupWindow;

import com.lancoo.lgschoolmonitor.R;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/23 15:14.
 */
public class WindowUtil {

    /**
     * 根据wid、hei属性获取PopupWindow
     *
     * @param view
     * @param wid
     * @param hei
     * @return
     */
    public static PopupWindow createWindow(View view, int wid, int hei) {

        PopupWindow pw = new PopupWindow(view, wid, hei);
        pw.setBackgroundDrawable(new ColorDrawable());
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);

        return pw;
    }

    /**
     * 创建AlertDialog
     *
     * @param context
     * @param title
     * @param message
     * @param negative
     * @param negativeListener
     * @param positive
     * @param positiveListener
     * @return
     */
    public static AlertDialog showSysAlertDialog(Context context, String title, String message,
                                                 String negative, DialogInterface.OnClickListener negativeListener, String positive,
                                                 DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setNegativeButton(negative, negativeListener)
                .setPositiveButton(positive, positiveListener);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    /**
     * 创建默认AlertDialog
     *
     * @param context
     * @param message
     * @param positiveListener
     * @return
     */
    public static AlertDialog showSysAlertDialog(Context context, String message,
                                                 DialogInterface.OnClickListener positiveListener) {
        String title = context.getString(R.string.hint);//提示
        String negative = context.getString(R.string.cancel);//取消
        String positive = context.getString(R.string.sure);//确认
        return showSysAlertDialog(context, title, message, negative, null, positive,
                positiveListener);
    }
}

package com.lancoo.lgschoolmonitor.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.lancoo.lgschoolmonitor.view.ProDialog;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/17 16:11.
 */
public class BaseFragment extends Fragment {

    private ProDialog mProdialog;


    protected void showProcessDialog(Context context) {
        if (mProdialog == null) {
            mProdialog = ProDialog.show(context);
        } else {
            if (!mProdialog.isShowing()) {
                mProdialog.show();
            }
        }
    }

    protected void dismissProcessDialog() {
        if (mProdialog != null && mProdialog.isShowing())
            mProdialog.dismiss();
    }
}

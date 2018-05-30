package com.lancoo.lgschoolmonitor.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.utils.ToastUtil;
import com.lancoo.lgschoolmonitor.view.ProDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Hinata-Liu
 * @date 2018/3/6 11:42.
 */

public class BaseActivity extends AppCompatActivity {
    private LinearLayout rootLayout;
    protected Toolbar toolbar;

    // 测试使用tag
    protected int mActionBarHei;// actionbar高度
    private BaseActivity oContext;
    private InputMethodManager inputManager;// 输入法管理对象
    private ProDialog mProdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setFitsSystemWindows(this, true);
        super.setContentView(R.layout.base_activity_layout);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }
        oContext = this;// 把当前的上下文对象赋值给BaseActivity
    }

    /**
     * 设置页面最外层布局 FitsSystemWindows 属性
     *
     * @param activity
     * @param value
     */
    public static void setFitsSystemWindows(Activity activity, boolean value) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(value);
        }
    }


    protected void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            mActionBarHei = TypedValue.complexToDimensionPixelSize(tv.data,
                    getResources().getDisplayMetrics());
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, mActionBarHei + getStatusBarHeight());
        toolbar.setLayoutParams(layoutParams);
        if (toolbar != null) {
            View toolbarContent = View.inflate(this, R.layout.main_toolbar_layout, null);
            toolbar.addView(toolbarContent);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }


    protected void initToolBar(int layoutID) {
        if (null != toolbar) {
            try {
                toolbar.removeAllViews();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        toolbar = findViewById(R.id.toolbar);
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            mActionBarHei = TypedValue.complexToDimensionPixelSize(tv.data,
                    getResources().getDisplayMetrics());
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, mActionBarHei + getStatusBarHeight());
        toolbar.setLayoutParams(layoutParams);
        if (toolbar != null) {
            View toolbarContent = View.inflate(this, layoutID, null);
            toolbar.addView(toolbarContent);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) {
            return;
        }
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    protected void initContentView(@LayoutRes int ResId) {
        super.setContentView(ResId);
    }


    /**
     * 设置标题
     *
     * @param resId
     */
    public void setCenterTitle(View view, int resId) {
        TextView tvActionBarCenter = (TextView) view.findViewById(R.id.tvActionBarCenter);
        tvActionBarCenter.setText(resId);
    }

    /**
     * 设置标题
     *
     * @param str
     */
    public void setCenterTitle(View view, String str) {
        TextView tvActionBarCenter = (TextView) view.findViewById(R.id.tvActionBarCenter);
        tvActionBarCenter.setText(str);
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setCenterTitle(int resId) {
        TextView tvActionBarCenter = (TextView) toolbar
                .findViewById(R.id.tvActionBarCenter);
        tvActionBarCenter.setText(resId);
    }

    /**
     * 设置标题
     *
     * @param str
     */
    public void setCenterTitle(String str) {
        TextView tvActionBarCenter = (TextView) toolbar
                .findViewById(R.id.tvActionBarCenter);
        tvActionBarCenter.setText(str);
    }

    /**
     * toast提示
     *
     * @param resId
     */
    public void toast(int resId) {
        ToastUtil.toast(getApplicationContext(), resId);
    }

    /**
     * toast提示
     *
     * @param str
     */
    public void toast(String str) {
        ToastUtil.toast(getApplicationContext(), str);
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        if (inputManager == null) {
            inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams
                .SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null) {
                inputManager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

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

    protected String longToTimeStr(long time,String formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern, Locale.getDefault());
        return sdf.format(new Date(time));
    }
    protected String longToTimeStr(long time) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        return sdf.format(new Date(time));
        return longToTimeStr(time,"yyyy-MM-dd HH:mm:ss");
    }

    protected String currentTimeToTimeStr(String formatPattern) {
        return longToTimeStr(System.currentTimeMillis(),formatPattern);
    }

    protected String currentTimeToTimeStr() {
        return longToTimeStr(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss");
    }
}

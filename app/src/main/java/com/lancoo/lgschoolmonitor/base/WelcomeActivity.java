package com.lancoo.lgschoolmonitor.base;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import com.lancoo.cpbase.authentication.base.AddressOperater;
import com.lancoo.cpbase.authentication.base.ExitBack;
import com.lancoo.cpbase.authentication.base.LoginBack;
import com.lancoo.cpbase.authentication.base.LoginOperate;
import com.lancoo.lgschoolmonitor.R;

/**
 *
 * @author Hinata-Liu
 * @date 2018/3/6 10:58
 *
 */
public class WelcomeActivity extends AppCompatActivity {

    private TextView tv_welcome_middle_text;
    private LoginOperate operate;
    private Handler mHandler;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_layout);
        tv_welcome_middle_text = (TextView) findViewById(R.id.tv_welcome_middle_text);
        tv_welcome_middle_text.setText(reduceTitle());
        operate = new LoginOperate(this);
        mHandler=new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int result = operate.getCurrentUserState();
                Log.e("WelcomeActivity","cause by:"+"当前用户状态result="+result);
                if(result==2){
                    translate(result);
                }else{
                    translate(result);
//                    getBaseLockInfo(result,address);
                }
            }
        }, 1 * 1000);
    }

    /**
     * 0—用户信息不存在，1—用户信息存在且与网络联通，2—用户信息存在但网络错误,4- 存在服务器地址不存在用户信息
     * @param result
     */
    private void translate(int result){
        final Intent intent=new Intent();
        intent.setClass(WelcomeActivity.this,BaseMonitorActivity.class);
        switch (result){
            case 1:
                intent.putExtra(Constant.FLAG_EXTRA_ACCESS_PATTERN,Constant.ACCESS_PATTERN_NORMAL);
                startActivity(intent);
                finish();
                break;
            default:
                goToLogin();
                break;
        }
    }
    private void goToLogin(){
        operate.goToLoginActivity(WelcomeActivity.this,
                new LoginBack() {
                    @Override
                    public void loginSuccess() {
                        AddressOperater addressOperater=new AddressOperater(WelcomeActivity.this);
                        String nowAddress=addressOperater.getBaseAddress();

                        Intent intent=new Intent();
                        intent.setClass(WelcomeActivity.this,BaseMonitorActivity.class);
                        intent.putExtra(Constant.FLAG_EXTRA_ACCESS_PATTERN,Constant.ACCESS_PATTERN_NORMAL);
                        startActivity(intent);
                        finish();

                    }
                }, new ExitBack() {
                    @Override
                    public void exit() {
                        finish();
                    }
                },false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 生成欢迎界面中间的文字描述信息
     * @return
     */
    private SpannableString reduceTitle(){
        String middle = getResources().getString(R.string.welcome_middle_text);
        String bottom = getResources().getString(R.string.welcome_bottom_text);
        SpannableString ss = new SpannableString(middle + "\n\t\t\t" + bottom);
        if (Build.VERSION.SDK_INT >= 23) {
            ss.setSpan(
                    new ForegroundColorSpan(getResources().getColor(
                            R.color.color_434343,null)), 0, middle.length(),
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            ss.setSpan(
                    new ForegroundColorSpan(getResources().getColor(
                            R.color.color_3F3F3F,null)), middle.length() + 4,
                    ss.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        } else {
            ss.setSpan(
                    new ForegroundColorSpan(getResources().getColor(
                            R.color.color_434343)), 0, middle.length(),
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            ss.setSpan(
                    new ForegroundColorSpan(getResources().getColor(
                            R.color.color_3F3F3F)), middle.length() + 4,
                    ss.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return  ss;
    }
}

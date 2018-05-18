package com.lancoo.lgschoolmonitor.base;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lancoo.cpbase.authentication.base.AddressOperater;
import com.lancoo.cpbase.authentication.base.CurrentUser;
import com.lancoo.cpbase.authentication.base.ExitBack;
import com.lancoo.cpbase.authentication.base.InfoListener;
import com.lancoo.cpbase.authentication.base.LoginBack;
import com.lancoo.cpbase.authentication.base.LoginOperate;
import com.lancoo.cpbase.authentication.base.TokenListener;
import com.lancoo.cpbase.authentication.base.TokenManager;
import com.lancoo.lgschoolmonitor.R;
import com.lancoo.lgschoolmonitor.bean.SysConfigInfoXmlBean;
import com.lancoo.lgschoolmonitor.me.fragments.MeFragment;
import com.lancoo.lgschoolmonitor.playback.api.InitLoader;
import com.lancoo.lgschoolmonitor.playback.fragments.PlayBackFragment;
import com.lancoo.lgschoolmonitor.schoolmonitor.fragments.MonitorFragment;
import com.lancoo.lgschoolmonitor.utils.RetrofitServiceManager;
import com.lancoo.lgschoolmonitor.view.AutoBgImageView;
import com.lancoo.lgschoolmonitor.view.TabView;

import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

/**
 * @author Hinata-Liu
 * @date 2018/3/6 10:58.
 */

public class BaseMonitorActivity extends BaseActivity implements View.OnClickListener {

    private Fragment mFragment;
    private MonitorFragment monitorFragment;
    private MeFragment meFragment;
    private PlayBackFragment pbFragment;
    private TabView tabView;
    private int[] titles;
    private String baseAddress;
    private LoginOperate mOperate;// 登录操作工具类
    private boolean isLogined = false;
    private TextView titleText;//标题文字
    private LinearLayout playBackLL;//录像回放按钮布局
    private AutoBgImageView downloadIcon;//录像回放下载按钮；
    private AutoBgImageView searchIcon;//录像回放搜索按钮；

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schoolmonitor_main_layout);
        initToolBar(R.layout.base_monitor_activity_toolbar_layout);
        findView();
        init();
    }


    private void findView() {
        tabView = findViewById(R.id.tabview);
    }


    private void init() {
        initActionBar();
        monitorFragment = new MonitorFragment();
        mFragment = monitorFragment;
        getSupportFragmentManager().beginTransaction().hide(mFragment)
                .add(R.id.content_frame, mFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(mFragment)
                .show(monitorFragment).commit();
        int topPadding = (int) getResources().getDimension(
                R.dimen.main_tab_top_padding);
        int bottomPadding = (int) getResources().getDimension(
                R.dimen.main_tab_bottom_padding);
        tabView.setItemPaddingTop(topPadding);
        tabView.setItemPadingBottom(bottomPadding);
        // 获取tab对应的图片id
        TypedArray ar = getResources().obtainTypedArray(R.array.draw);
        TypedArray tr = getResources().obtainTypedArray(R.array.titles);
        int[] resIds = new int[ar.length()];
        titles = new int[tr.length()];
        for (int i = 0; i < ar.length(); i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        for (int i = 0; i < tr.length(); i++) {
            titles[i] = tr.getResourceId(i, 0);
        }
        ar.recycle();
        tabView.addItems(resIds, R.color.base_color);
        tabView.setCurrent(0);
        tabView.setOnTabItemClickListener(new TabClickListener());
        baseAddress = new AddressOperater(this).getBaseAddress();
        if (!TextUtils.isEmpty(CurrentUser.UserID)) {
            isLogined = true;
        }
        mOperate = new LoginOperate(this);
        TokenManager.getInstance().addTokenListener(tokenListener);
        TokenManager.getInstance().setInfoListener(infoListener);
        getInsideUrl();
        getOuterUrl();
    }

    private void initActionBar() {
        titleText = findViewById(R.id.titleText);
        playBackLL = findViewById(R.id.playBackLL);
        downloadIcon = findViewById(R.id.downloadIcon);
        searchIcon = findViewById(R.id.searchIcon);
        titleText.setText(R.string.schoolmonitor);
        downloadIcon.setOnClickListener(this);
        searchIcon.setOnClickListener(this);
        playBackLL.setVisibility(View.INVISIBLE);
    }

    private void changeActionBarTitle(int type) {
        switch (type) {
            case 0:
                titleText.setText(R.string.schoolmonitor);
                playBackLL.setVisibility(View.INVISIBLE);
                break;
            case 1:
                titleText.setText(R.string.playback);
                playBackLL.setVisibility(View.VISIBLE);
                break;
            case 2:
                titleText.setText(R.string.me);
                playBackLL.setVisibility(View.INVISIBLE);
                break;
            default:
                titleText.setText(R.string.schoolmonitor);
                playBackLL.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.searchIcon:
                toast("搜索功能还没搞！");
                break;
            case R.id.downloadIcon:
                toast("下载功能还没搞！");
                break;
            default:

                break;
        }
    }


    /**
     * Tab切换监听
     *
     * @author Hinata-Liu
     * @version 创建时间：2016年8月10日 上午10:25:37
     */
    private class TabClickListener implements TabView.OnTabItemClickListener {
        @Override
        public void onTabItemClick(int position) {

            switch (position) {
                case 0:
                    if (monitorFragment == null) {
                        monitorFragment = new MonitorFragment();
                    }
                    changeActionBarTitle(0);
                    switchContent(monitorFragment);
                    break;
                case 1:
                    if (pbFragment == null) {
                        pbFragment = new PlayBackFragment();
                    }
                    changeActionBarTitle(1);
                    switchContent(pbFragment);

                    break;
                case 2:
                    if (meFragment == null) {
                        meFragment = new MeFragment();
                    }
                    changeActionBarTitle(2);
                    switchContent(meFragment);
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 切换Fragment
     *
     * @param fragment 需要显示的Fragment
     */
    private void switchContent(Fragment fragment) {
        if (mFragment != fragment) {
            if (!fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.content_frame, fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    /*
     * ************************************身份认证监听、用户信息监听*************************
     */
    private TokenListener tokenListener = new TokenListener() {
        @Override
        public void tokenInvalid(int flag) {
            if (flag == 0) {// 用户失效
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mOperate.openLoginDialog(new LoginBack() {
                            @Override
                            public void loginSuccess() {// 登录成功
                                if (Constant.ProductType != 1 || Constant.ProductType != 0)
                                    //基础版不启动消息轮训
                                    userInfoUpdate();
                            }
                        }, false, true, new ExitBack() {
                            @Override
                            public void exit() {
                                finish();
                            }
                        });
                    }

                });
            } else if (flag == 1) {// 其他应用退出导致失效
                //做整个app 的退出操作
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        finish();
                    }
                });
            }
        }
    };

    // 用户信息更新回调
    private InfoListener infoListener = new InfoListener() {
        @Override
        public void infoRefresh() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    userInfoUpdate();
                }
            });
        }
    };

    /*
     * ************************************身份信息更新********************************
     */
    private void userInfoUpdate() {
        // 1.更新实时交流信息
        String address = baseAddress;
        String token = CurrentUser.Token;
        String userID = CurrentUser.UserID;
        String userName = CurrentUser.UserName;
        String userHDPath = CurrentUser.PhotoPath;
        int userType = CurrentUser.UserType;
    }


    @Override
    protected void onDestroy() {
        TokenManager.getInstance().removeTokenListener(tokenListener);
        TokenManager.getInstance().setInfoListener(null);
        if (mOperate != null) {
            mOperate.stopService();
        }
        super.onDestroy();
    }

    /**
     * 获取教室内数据的基础地址
     *
     * @author Hinata-Liu
     * @date 2018/5/16 17:26
     */
    @SuppressLint("CheckResult")
    private void getInsideUrl() {
        Retrofit retrofit = RetrofitServiceManager.getXmlRetrofit(baseAddress);
        InitLoader initLoader = new InitLoader(retrofit);
        initLoader.getSysConfigInfo("E00", "").subscribe(new Consumer<SysConfigInfoXmlBean>() {
            @Override
            public void accept(SysConfigInfoXmlBean sysConfigInfoXmlBean) throws Exception {
                List<String> resultstr = sysConfigInfoXmlBean.getConfigInfos();
                if (resultstr == null || resultstr.size() <= 0) return;
                Global.mInsideBaseUrl = resultstr.get(resultstr.size() - 1);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }

    /**
     * 获取教室外数据的基础地址
     *
     * @author Hinata-Liu
     * @date 2018/5/16 17:26
     */
    @SuppressLint("CheckResult")
    private void getOuterUrl() {
        Retrofit retrofit = RetrofitServiceManager.getXmlRetrofit(baseAddress);
        InitLoader initLoader = new InitLoader(retrofit);
        initLoader.getSysConfigInfo("M10", "").subscribe(new Consumer<SysConfigInfoXmlBean>() {
            @Override
            public void accept(SysConfigInfoXmlBean sysConfigInfoXmlBean) throws Exception {
                List<String> resultstr = sysConfigInfoXmlBean.getConfigInfos();
                if (resultstr == null || resultstr.size() <= 0) return;
                switch (resultstr.size()) {
                    case 2:
                        Global.mOuterBaseUrl = resultstr.get(1);
                        break;
                    case 3:
                    case 4:
                        Global.mOuterBaseUrl = resultstr.get(resultstr.size() - 2);
                        break;
                    default:
                        break;
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }
}

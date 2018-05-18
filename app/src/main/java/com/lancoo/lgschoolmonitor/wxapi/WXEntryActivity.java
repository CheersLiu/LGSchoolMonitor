package com.lancoo.lgschoolmonitor.wxapi;


import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.lancoo.cpbase.authentication.activities.LoginActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * author tyw version 2016年8月22日 上午9:15:46 第三方登录微信回调页
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
	private final String TAG = "com.lancoo.cpbase";
	private WXNetUtil netUtil;
	private IWXAPI api;
	private String WEIXIN_APPID;
	private String WEIXIN_SECRET;
	private String WEIXIN_ACCESS_TOKEN;
	private String WEIXIN_OPENID;
	private int fromflag=0;//微信登录的来源默认为身份认证模块,为1时代表来源于账户设置的第三方账户绑定
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		netUtil = new WXNetUtil();
		getAppidFromConfg();// 从配置文件中获取到微信的appid和appsecret信息
		api = WXAPIFactory.createWXAPI(this, WEIXIN_APPID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		// LogUtil.e(TAG, "onNewIntent方法");
//		Log.e(TAG, "onNewIntent方法");
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	/**
	 * 微信的回调方法
	 */
	@Override
	public void onReq(BaseReq req) {
		// TODO Auto-generated method stub
		// LogUtil.e(TAG, "onReq中req="+req.toString());
//		Log.e(TAG, TAG + "onReq中req=" + req.toString());
	}

	/**
	 * 微信的回调方法
	 */
	@Override
	public void onResp(BaseResp resp) {
		if (resp.errCode == BaseResp.ErrCode.ERR_AUTH_DENIED) {
			// 用户拒绝了微信登录授权
			Toast.makeText(this, "微信授权错误,", Toast.LENGTH_SHORT).show();
			finish();
		} else if (resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
			// 用户取消了微信登录授权
			Toast.makeText(this, "微信授权取消,", Toast.LENGTH_SHORT).show();
			finish();
		} else if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
			// 用户同意微信登录授权
			SendAuth.Resp sresp = (SendAuth.Resp) resp;
			/*
			 * state=lancoo_weixin_login 来源于身份认证模块的微信登录 
			 * state=lancoo_weixin_login_thirdbind 来源账户设置第三方帐号绑定
			 */
			if(sresp.state.equals("lancoo_weixin_login_thirdbind"))  fromflag=1;
			getAccessToken(sresp.code);
		}else{
			Toast.makeText(this, "微信授权失败,未知错误", Toast.LENGTH_SHORT).show();
			finish();
		}
//		finish();
	}

	/**
	 * 获取用户的信息的第一步获取access_token的值 正确的返回格式
	 * access_token,expires_in,refresh_token,openid,scope,unionid 错误返回格式
	 * {"errcode":40029,"errmsg":"invalid code"}
	 */
	private void getAccessToken(String code) {
		String url = "oauth2/access_token";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appid", WEIXIN_APPID);
		map.put("secret", WEIXIN_SECRET);
		map.put("code", code);
		map.put("grant_type", "authorization_code");
		String params = netUtil.changeParams(map);
//		Log.e(TAG, "getAccessToken的url=" + url + params.toString());
		JSONObject json = getWXNetData(url, params);
		if (json == null || json.has("errcode")){
			// 未获取到数据或者未获取到正确的数据直接return
			Toast.makeText(this, "微信授权失败", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		try {
			WEIXIN_ACCESS_TOKEN = json.getString("access_token");
			WEIXIN_OPENID = json.getString("openid");
			getWXUserinfo();// 进一步获取微信用户信息
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "微信授权失败", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
	}

	/**
	 * 利用上一步获取到的access_token和openid信息获取到用户的详细信息 正确的返回格式
	 * openid,nickname,sex,province,city,country,headimgurl,privilege,unionid
	 * 错误的返回格式 {"errcode":40029,"errmsg":"invalid code"}
	 */
	private void getWXUserinfo() {
		HashMap<String, String> params = new HashMap<String, String>();
		// https: //
		// api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
		params.put("access_token", WEIXIN_ACCESS_TOKEN);
		params.put("openid", WEIXIN_OPENID);
		JSONObject json = getWXNetData("userinfo", netUtil.changeParams(params));
//		Log.e(TAG, "getWxUserinfo的数据=" + json);
		if (json == null || json.has("errcode")){
			Toast.makeText(this, "微信授权失败", Toast.LENGTH_SHORT).show();
			finish();
			return;// 未获取到数据或者未获取到正确的数据直接return
		}
		String nickname;
		String headimgurl;
		try {
			// json.getString("openid");
			nickname = json.getString("nickname");
			headimgurl = json.getString("headimgurl");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "微信授权失败", Toast.LENGTH_SHORT).show();
			finish();
			// e.printStackTrace();
			return;
		}
		// 返回登录包的界面
		Intent intent = new Intent();
		//fromfalg=1表示微信登录操作来源于账户设置否则来着身份认证模块的微信登录 如果没有加入账户设置包不用考虑账户设置的绑定 可以注释掉
		Bundle bundle = new Bundle();
		bundle.putString("WEIXIN_OPENID", WEIXIN_OPENID);
		bundle.putString("WEIXIN_NICKNAME", nickname);
		// 取64*64的头像图片.默认值是640*640的头像
		headimgurl = headimgurl.substring(0, headimgurl.length() - 1) + "64";
		bundle.putString("WEIXIN_HEADURL", headimgurl);
		if(fromflag==1){
//			intent.setFlags(0x15975);
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("loginflag", "bind");
			//未引用账户设置包会导致下面的代码报错 如果你只需要身份认证库 吧下面的代码注释掉就好不用管
//			intent.setClass(this, SlideThirdActivity.class);//账户设置的第三方账户绑定界面
		}
		else{
			intent.setFlags(0x78789);
			intent.setClass(this, LoginActivity.class);
		}
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}

	/**
	 * 异步网络获取数据
	 * 
	 * @param params
	 *            访问的url
	 * @return 网络请求的结果
	 */
	private JSONObject getWXNetData(String url, String params) {
		try {
			return new AsyncTask<String, Void, JSONObject>() {

				@Override
				protected JSONObject doInBackground(String... params) {
					// TODO Auto-generated method stub
					String url = "https://api.weixin.qq.com/sns/" + params[0]
							+ params[1];
					try {
						return netUtil.get(url.trim(), 4000);
					} catch (SocketTimeoutException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, TAG + "请求网络失败");
						e.printStackTrace();
						return null;
					}
				}
			}.execute(url, params).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从Androidmaniest中获取到微信的appid和appsecret信息
	 */
	private void getAppidFromConfg() {
		try {
			ApplicationInfo info = getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			WEIXIN_APPID = info.metaData.getString("WEIXIN_APPID");
			WEIXIN_SECRET = info.metaData.getString("WEIXIN_SECRET");
		} catch (PackageManager.NameNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Log.e(TAG, "从配置文件中读取微信配置信息失败!!!");
			finish();
		}
	}
}

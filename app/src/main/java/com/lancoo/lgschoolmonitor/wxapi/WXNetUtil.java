package com.lancoo.lgschoolmonitor.wxapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 网络访问工具类
 * @author tyw
 * @createtime 2016年8月23日
 */
public class WXNetUtil {
	private final String TAG="WXNetUtil";
	public static final int FLAG_ERROR = 0;// 网络访问错误
	public static final int FLAG_NO_NETWORK = 1;// 没有网络
	public static final int FLAG_TIMEOUT = 2;// 超时

	private final int TIMEOUT = 6000;

	/**
	 * 获取手机的网络连接状态。
	 * 
	 * @return 0（网络断开），1（wifi网络），2（2G、3G、4G网络）。
	 */
	public int getNetworkState(Context context) {
		ConnectivityManager conMan = (ConnectivityManager) context
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
	public JSONObject get(String apiUrl) throws SocketTimeoutException {
		return get(apiUrl, TIMEOUT);
	}

	/**
	 * 使用get方式访问webapi
	 * 
	 * @param apiUrl
	 *            url
	 * @param timeOut
	 *            超时时间，单位：ms
	 * @return
	 * @throws SocketTimeoutException
	 * @throws Exception
	 */
	public JSONObject get(String apiUrl, int timeOut) throws SocketTimeoutException {
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(timeOut);
			con.setReadTimeout(timeOut);
			// 将读超时设置为指定的超时，以毫秒为单位。用一个非零值指定在建立到资源的连接后从
			// Input 流读入时的超时时间。如果在数据可读取之前超时期满，则会引发一个
			// java.net.SocketTimeoutException。
			con.setDoInput(true);// 指示应用程序要从 URL 连接读取数据。
			con.setRequestMethod("GET");// 设置请求方式
			if (con.getResponseCode() == 200) {// 当请求成功时，接收数据（状态码“200”为成功连接的意思“ok”）
				InputStream is = con.getInputStream();
				String str = formatIsToString(is);
				if(!TextUtils.isEmpty(str)) return new JSONObject(str);
			}
			return null;
		} catch (Exception e) {
			if (e instanceof SocketTimeoutException) {
				throw new SocketTimeoutException();
			} else
				return null;
		}
	}
	// 将流转化为字符串
	private String formatIsToString(InputStream is) {
		if (is == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		try {
			while ((len = is.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}
			baos.flush();
			baos.close();
			is.close();
			return new String(baos.toByteArray(), "utf-8");
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 将方法名和参数转化为访问微信api的格式
	 * 微信api的基础地址https://api.weixin.qq.com/sns
	 * 获取access_token微信接口 https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	 * 获取用户信息微信接口  https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
	 * @param strs
	 * @return
	 */
	public String changeParams(HashMap<String, String> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		for (Entry<String, String> entry : params.entrySet()){
			sb.append(entry.getKey()).append("=").append(encodeParams(entry.getValue())).append("&");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}

	/**
	 * 对参数进行编码
	 * 
	 * @param params
	 * @return
	 */
	private String encodeParams(String params) {
		return Uri.encode(params, "utf-8");
	}
}

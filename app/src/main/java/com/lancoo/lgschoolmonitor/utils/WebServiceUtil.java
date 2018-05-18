package com.lancoo.lgschoolmonitor.utils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * @author tangjiabing
 */
public class WebServiceUtil {

    public static final String EXP_NO_NETWORK = "EXP_NO_NETWORK";
    public static final String EXP_NETWORK = "EXP_NETWORK";
    public static final String EXP_PARSER = "EXP_PARSER";
    public static final String EXP_NOT_FIND_SERVER = "EXP_NOT_FIND_SERVER";


    /**
     * 从WebService接口中获取数据
     *
     * @param nameSpace   命名空间
     * @param methodName  方法名称
     * @param paramNames  方法中的参数名，可为null
     * @param paramValues 方法中的参数值，可为null
     * @param serviceUrl  WSDL文档的URL
     * @param timeout     连接超时时间，单位为毫秒
     * @return xml格式的数据，可以为null。若返回为“EXP_NO_NETWORK”，则表示无网络；若为“EXP_NETWORK”，则表示网络传输异常
     * ；若为“EXP_PARSER”，则表示解析xml异常；若为“EXP_NOT_FIND_SERVER”，则表示找不到服务器（
     * 有可能是IP被抢占）
     */
    public static Object getWSDataWithObject(String nameSpace,
                                             String methodName, String[] paramNames, String[]
                                                     paramValues,
                                             String serviceUrl, int timeout) {

        int netState = NetUtil.getNetState();
        if (netState == 0)
            return EXP_NO_NETWORK;

        SoapObject request = new SoapObject(nameSpace, methodName);
        if (paramValues != null) {
            for (int i = 0; i < paramValues.length; i++)
                request.addProperty(paramNames[i], paramValues[i]);
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.bodyOut = request;
        envelope.dotNet = true;
        HttpTransportSE transportSE = new HttpTransportSE(serviceUrl, timeout);
        transportSE.debug = true;

        try {
            transportSE.call(nameSpace + methodName, envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            return result;
        } catch (IOException e) {
            return EXP_NETWORK;
        } catch (XmlPullParserException e) {
            return EXP_PARSER;
        } catch (NullPointerException e) {
            return EXP_NOT_FIND_SERVER;
        } catch (ClassCastException e) {
            Object result = envelope.bodyIn;
            return result;
        }

    }


    public static Object getWSDataWithObject(String nameSpace,
                                             String methodName, String serviceUrl, int timeout) {
        return getWSDataWithObject(nameSpace,
                methodName, null, null,
                serviceUrl, timeout);
    }

}

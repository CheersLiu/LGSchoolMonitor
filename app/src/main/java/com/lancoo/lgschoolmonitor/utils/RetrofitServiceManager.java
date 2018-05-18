package com.lancoo.lgschoolmonitor.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/17 11:21.
 */
public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT=5;
    private static final int DEFAULT_READ_TIME_OUT=10;
    private Retrofit retrofit;
    private static  volatile OkHttpClient okHttpClient;
    private RetrofitServiceManager(){
        initRetrofit();
    }
    private Retrofit initRetrofit(){
        Retrofit.Builder retrofitBuild=new Retrofit.Builder();
        retrofitBuild.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
//        retrofitBuild.addConverterFactory(GsonConverterFactory.create());
        //TODO
        //FixMe
        //暂时使用String作为转化器，后期时间更充裕的时候改为Gson转化器
        retrofitBuild.addConverterFactory(new StringConverterFactory());
        retrofitBuild.client(initOkHttpClient());
        retrofit=retrofitBuild.build();
        return  retrofit;
    }
    private static OkHttpClient initOkHttpClient(){
        if(okHttpClient==null){
            synchronized (RetrofitServiceManager.class){
                if (okHttpClient==null){
                    OkHttpClient.Builder okhttpbuilder=new OkHttpClient.Builder();
                    okhttpbuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
                    okhttpbuilder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);
                    HttpCommonInterceptor.Builder interceptorBuild=new HttpCommonInterceptor.Builder();
                    interceptorBuild.addHeaderParams("paltform","android");
                    HttpCommonInterceptor httpCommonInterceptor=interceptorBuild.build();
                    okhttpbuilder.addInterceptor(httpCommonInterceptor);
                    okHttpClient=okhttpbuilder.build();
                }
            }
        }
        return  okHttpClient;
    }


    private static class SingletonHolder {
        private static final RetrofitServiceManager INSTANCE=new RetrofitServiceManager();
    }
    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
    public static Retrofit getRetrofit(String baseUrl){
        Retrofit.Builder retrofitBuild=new Retrofit.Builder();
        retrofitBuild.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
//        retrofitBuild.addConverterFactory(GsonConverterFactory.create());
        //TODO
        //FixMe
        //暂时使用String作为转化器，后期时间更充裕的时候改为Gson转化器
        retrofitBuild.baseUrl(baseUrl);
        retrofitBuild.addConverterFactory(new StringConverterFactory());
        retrofitBuild.client(initOkHttpClient());
        return retrofitBuild.build();
    }
    public static Retrofit getGsonRetrofit(String baseUrl){
        Retrofit.Builder retrofitBuild=new Retrofit.Builder();
        retrofitBuild.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuild.addConverterFactory(GsonConverterFactory.create());
        //TODO
        //FixMe
        //暂时使用String作为转化器，后期时间更充裕的时候改为Gson转化器
        retrofitBuild.baseUrl(baseUrl);
        retrofitBuild.client(initOkHttpClient());
        return retrofitBuild.build();
    }
    public static Retrofit getXmlRetrofit(String baseUrl){
        Retrofit.Builder retrofitBuild=new Retrofit.Builder();
        retrofitBuild.addConverterFactory(SimpleXmlConverterFactory.create());
        retrofitBuild.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuild.baseUrl(baseUrl);
        retrofitBuild.client(initOkHttpClient());
        return retrofitBuild.build();
    }
}


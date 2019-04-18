package com.example.ray.codecollections.view.functionactivity.network;

import android.content.Context;

import com.example.ray.codecollections.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallRetrofitService {
    public  String basu_url = "https://api.apiopen.top/";

    private static CallRetrofitService sInstance;
    private GetRetrofitService mService;
    private Context mContext;

    private CallRetrofitService(Context context){
        mContext = context.getApplicationContext();
    }

    public static CallRetrofitService getInstance() {
        if (sInstance == null) {
            sInstance = new CallRetrofitService(MyApplication.getContext());
        }
        return sInstance;
    }
    public synchronized GetRetrofitService getRetrofitService(){
        //OkHttpClient实例 和配置
        OkHttpClient client = new  OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间
                .build();
        //创建一个Retrofit 实例，并且完成相关的配置
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(basu_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(GetRetrofitService.class);
    }

}

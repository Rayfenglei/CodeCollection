package com.example.ray.codecollections.view.functionactivity.network;

import android.content.Context;

import com.example.ray.codecollections.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ObservableRetrofitService {
    public  String basu_url = "https://api.apiopen.top/";
    private static ObservableRetrofitService sInstance;
    private GetRetrofitService mService;
    private Context mContext;
    private ObservableRetrofitService(Context context){
        mContext = context.getApplicationContext();
    }

    public static ObservableRetrofitService getInstance() {
        if (sInstance == null) {
            sInstance = new ObservableRetrofitService(MyApplication.getContext());
        }
        return sInstance;
    }

    public synchronized GetRetrofitService getRetrofitService(){
        if (mService ==null){
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时时间
                    .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(basu_url)
                                .client(okHttpClient)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

            mService = retrofit.create(GetRetrofitService.class);
        }
        return mService;
    }
}

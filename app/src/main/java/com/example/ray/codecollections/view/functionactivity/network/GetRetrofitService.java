package com.example.ray.codecollections.view.functionactivity.network;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRetrofitService {

    @GET("videoHomeTab")
    Call<CategoryContent<List<CategoryBean>>> getVideoCategory();

    @GET("videoCategoryDetails?")
    Call<ResponseBody> getVideoCategoryDetails(@Query("id") int id);
    //说明：定义了一个方法getTop250,使用get请求方式，加上@GET 标签，
    // 标签后面是这个接口的 尾址top250,完整的地址应该是 baseUrl+尾址 ，
    // 参数 使用@Query标签，如果参数多的话可以用@QueryMap标签，接收一个Map
    //Call可以指定解析的数据类型
    //ResponseBody表示原始数据可以 response.body().string() 输出
    @GET("videoHomeTab")
    Observable<CategoryContent<List<CategoryBean>>> observableVideoCategory();

    @GET("videoCategoryDetails?")
    Observable<ResponseBody> observableVideoCategoryDetails(@Query("id") String id);

}

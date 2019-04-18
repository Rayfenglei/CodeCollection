package com.example.ray.codecollections.view.functionactivity.network;


import android.util.Log;
import org.reactivestreams.Subscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VideoModelImpl {
    private ArrayList<CategoryBean> categoryBeans;
    private ArrayList<VideoBean> videoBeans;

    public VideoModelImpl() {
    }

    public void getVideos(){
        Observable<ResponseBody> observable = getCategoryBean().flatMap(new Function<CategoryContent<List<CategoryBean>>, Observable<ResponseBody> >() {
            @Override
            public Observable<ResponseBody>  apply(CategoryContent<List<CategoryBean>> listCategoryContent) throws Exception {
                return getVideos(listCategoryContent.getResult().get(4).getId());
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>(){
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.i("VideoModelImpl ", "getVideos : " + responseBody.string());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }
    private Observable<ResponseBody> getVideos(String number){
        return ObservableRetrofitService.getInstance().getRetrofitService().observableVideoCategoryDetails(number);
    }
    private Observable<CategoryContent<List<CategoryBean>>> getCategoryBean(){
        Observable<CategoryContent<List<CategoryBean>>> observable;
        observable = ObservableRetrofitService.getInstance().getRetrofitService().observableVideoCategory()
                .doOnNext(new Consumer<CategoryContent<List<CategoryBean>>>() {
                    @Override
                    public void accept(CategoryContent<List<CategoryBean>> listCategoryContent) throws Exception {
                        Log.i("VideoModelImpl ","getCategoryBean : "+listCategoryContent.getResult().toString());
                    }
                });
        return observable;

        //        Observable<CategoryContent<List<CategoryBean>>> observable = ObservableRetrofitService.getInstance().getRetrofitService().observableVideoCategory();
//        observable.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<CategoryContent<List<CategoryBean>>>() {
//                        @Override
//                        public void onSubscribe(Disposable d) { }
//                        @Override
//                        public void onNext(CategoryContent<List<CategoryBean>> listCategoryContent) {
//                            Log.i("uidObservable"," "+listCategoryContent.getResult().toString());
//                        }
//                        @Override
//                        public void onError(Throwable e) { }
//                        @Override
//                        public void onComplete() { }
//                    });

    }
    public static void getCategoryDetail(){
        Observable<ResponseBody> observable = ObservableRetrofitService.getInstance().getRetrofitService().observableVideoCategoryDetails("14");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.i("getCategoryDetail", " " + responseBody.string());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}

package com.example.ray.codecollections.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseActivity1;
import com.example.ray.codecollections.base.BasePresenter;
import com.example.ray.codecollections.view.functionactivity.FunctionActivity;
import com.example.ray.codecollections.view.recycleractivity.NavigationRecyclerActivity;
import com.example.ray.codecollections.view.scrollactivity.ScrollingActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity1 implements View.OnClickListener{
    private static final String TAG = "RX2JavaTest";
    private Button btRecyclers;
    private Button btFunctions;
    private Button btScrolls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
        RxJava2Test();
    }

    @Override
    public void initView() {
        btRecyclers = findViewById(R.id.bt_to_recyclers);
        btFunctions = findViewById(R.id.bt_to_functions);
        btScrolls = findViewById(R.id.bt_to_scroll);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btRecyclers.setOnClickListener(this);
        btFunctions.setOnClickListener(this);
        btScrolls.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_to_recyclers:
                startActivity(new Intent(this, NavigationRecyclerActivity.class));
                break;
            case R.id.bt_to_functions:
                startActivity(new Intent(this, FunctionActivity.class));
                break;
            case R.id.bt_to_scroll:
                startActivity(new Intent(this, ScrollingActivity.class));
                break;
            default:
                break;
        }
    }
    private void RxJava2Test(){
        Observable.create(new ObservableOnSubscribe<Integer>() {//第一步 初始化 observable
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "Observable emit 1" + "\n");
                emitter.onNext(1);
                Log.i(TAG, "Observable emit 2" + "\n");
                emitter.onNext(2);
                Log.i(TAG, "Observable emit 3" + "\n");
                emitter.onNext(3);
                emitter.onComplete();
                Log.i(TAG, "Observable emit 4" + "\n" );
                emitter.onNext(4);

            }
        }).subscribeOn(Schedulers.io())//指定的就是发射事件的线程
          .observeOn(AndroidSchedulers.mainThread())//指定的就是订阅者接收事件的线程
          .subscribe(new Observer<Integer>() { //第三步 订阅
            //第二步 初始化 observer
            private int i;
            private Disposable disposable;//用于解除订阅
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    disposable.dispose();
                }
                Log.i(TAG, "onNext value" +integer+ "\n" );
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError : value : " + e.getMessage() + "\n" );
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete" + "\n" );
            }
        });
    }
}

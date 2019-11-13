package com.example.ray.codecollections.designmodel.observer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;

/*
* 观察者模式 发布-订阅
* EventBus BroadcastReceiver RxJava
* */

public class ObserverFragment extends BaseFragment1 implements View.OnClickListener{
    private Button button1,button2;
    // 1、声明被观察者
    private PersonObservable observable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_observer);
        initView();
        initEvent();
        initData();
        return view;
    }

    @Override
    public void initView() {
        button1 = view.findViewById(R.id.bt_observer1);
        button2 = view.findViewById(R.id.bt_observer2);
    }

    @Override
    public void initData() {
        observable = new PersonObservable();
        //向被观察者注册观察者
        observable.addObserver(new PersonObserver(1));
        observable.addObserver(new PersonObserver(2));
    }

    @Override
    public void initEvent() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_observer1:
                //3、被观察者 状态改变
                observable.setAge(15);
                observable.setName("haha");
                break;
            case R.id.bt_observer2:

                break;
        }
    }
}

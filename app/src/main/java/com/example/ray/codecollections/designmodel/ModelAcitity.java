package com.example.ray.codecollections.designmodel;

import android.os.Bundle;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseActivity1;
import com.example.ray.codecollections.base.BasePresenter;

public class ModelAcitity extends BaseActivity1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}

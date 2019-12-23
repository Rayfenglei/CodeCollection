package com.example.ray.codecollections.view.BesselLineActivity.twofragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;
import com.example.ray.codecollections.defineviews.WaveView;

public class TwoDefineFragment extends BaseFragment1 {
    private WaveView mWaveView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_define_two);
        initView();
        initEvent();

        return view;
    }



    @Override
    public void initView() {
        mWaveView = view.findViewById(R.id.define_waveview);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mWaveView.setAnimator();
    }
}

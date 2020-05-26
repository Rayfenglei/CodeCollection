package com.example.ray.codecollections.view.BesselLineActivity.fourfragemnt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;
import com.example.ray.codecollections.defineviews.LinesView;

public class FourDefineFragment extends BaseFragment1 {
    private LinesView linesView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_define_four);
        initView();
        initEvent();
        return view;
    }

    @Override
    public void initView() {
        linesView = view.findViewById(R.id.view_line);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        linesView.setAnimator();
    }
}

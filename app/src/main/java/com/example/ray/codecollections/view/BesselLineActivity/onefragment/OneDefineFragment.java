package com.example.ray.codecollections.view.BesselLineActivity.onefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;
import com.example.ray.codecollections.defineviews.BesselLinetoView;
import com.example.ray.codecollections.defineviews.BesselQaudtoView;

/**
 * 手指轨迹
 * lineto和qaudto两种实现方式 后者采用贝塞尔曲线更加平滑
 */

public class OneDefineFragment extends BaseFragment1 {
    private Button button;
    private BesselLinetoView besselLinetoView;
    private BesselQaudtoView besselQaudtoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_define_one);
        initView();
        initEvent();
        return view;
    }

    @Override
    public void initView() {
        button = view.findViewById(R.id.bt_reset);
        besselLinetoView = view.findViewById(R.id.bessel_lineto);
        besselQaudtoView = view.findViewById(R.id.bessel_qaudto);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                besselLinetoView.reset();
                besselQaudtoView.reset();
            }
        });
    }
}

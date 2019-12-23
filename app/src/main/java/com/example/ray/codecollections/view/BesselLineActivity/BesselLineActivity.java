package com.example.ray.codecollections.view.BesselLineActivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseActivity1;
import com.example.ray.codecollections.base.BasePresenter;
import com.example.ray.codecollections.defineviews.ViewPagerNotSlide;
import com.example.ray.codecollections.view.BesselLineActivity.fourfragemnt.FourDefineFragment;
import com.example.ray.codecollections.view.BesselLineActivity.onefragment.OneDefineFragment;
import com.example.ray.codecollections.view.BesselLineActivity.threefragment.ThreeDefineFragment;
import com.example.ray.codecollections.view.BesselLineActivity.twofragment.TwoDefineFragment;

import java.util.ArrayList;
import java.util.List;

public class BesselLineActivity extends BaseActivity1 {
    private TabLayout tabLayout;
    private ViewPagerNotSlide viewPager;
    private List<Fragment> fragmentList;
    private BesselAdapter besselAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bessel_line);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void initView() {
        viewPager = findViewById(R.id.viewpager_bessel);
        tabLayout = findViewById(R.id.tablayout_bessel);
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new OneDefineFragment());
        fragmentList.add(new TwoDefineFragment());
        fragmentList.add(new ThreeDefineFragment());
        fragmentList.add(new FourDefineFragment());

        besselAdapter = new BesselAdapter(getSupportFragmentManager(), fragmentList, this);
        viewPager.setAdapter(besselAdapter);

        //TabLayout关联ViewPager
        tabLayout.setupWithViewPager(viewPager);
        setTabLayout();
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void setTabLayout() {
        //设置监听
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //改变Tab 状态
                (tab.getCustomView().findViewById(R.id.iv_tab)).setSelected(true);
                (tab.getCustomView().findViewById(R.id.tv_tab)).setSelected(true);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                (tab.getCustomView().findViewById(R.id.iv_tab)).setSelected(false);
                (tab.getCustomView().findViewById(R.id.tv_tab)).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // 为绑定viewpager后的TabLayout的tabs设置自定义view
        for (int i = 0; i < fragmentList.size(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(setCustomView(i));
        }
        //打开指定界面
        tabLayout.getTabAt(0).select();
    }

    //自定义导航栏
    public View setCustomView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.navigation_define_tab, null);
        TextView tv = view.findViewById(R.id.tv_tab);
        ImageView iv = view.findViewById(R.id.iv_tab);
        switch (position) {
            case 0:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.tab_define_strategy_selector, null));
                tv.setText("One");
                break;
            case 1:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.tab_define_food_selector, null));
                tv.setText("Two");
                break;
            case 2:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.tab_define_track_selector, null));
                tv.setText("Three");
                break;
            case 3:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.tab_define_my_selector, null));
                tv.setText("Four");
                break;
        }
        return view;

    }
}

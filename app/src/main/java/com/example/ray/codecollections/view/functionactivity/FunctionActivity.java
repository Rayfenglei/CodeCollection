package com.example.ray.codecollections.view.functionactivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseActivity1;
import com.example.ray.codecollections.base.BasePresenter;
import com.example.ray.codecollections.designmodel.observer.ObserverFragment;
import com.example.ray.codecollections.view.functionactivity.network.NetworkFragment;
import com.example.ray.codecollections.view.functionactivity.searchbanner.SearchFragment;
import com.example.ray.codecollections.view.functionactivity.sqlite.SQLiteFragment;
import java.util.ArrayList;
import java.util.List;

public class FunctionActivity extends BaseActivity1 {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> mFragmentList;
    private FunctionAdapter mFunctionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        initView();
        initData();
        initEvent();
        setTabLayout();
    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.viewpager_function);
        mTabLayout = findViewById(R.id.tablayout_function);

    }

    @Override
    public void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new ObserverFragment());
        mFragmentList.add(new NetworkFragment());
        mFragmentList.add(new SearchFragment());
        mFragmentList.add(new SQLiteFragment());

        mFunctionAdapter = new FunctionAdapter(getSupportFragmentManager(),mFragmentList,this);
        mViewPager.setAdapter(mFunctionAdapter);
        //TabLayout关联ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void initEvent() {

    }
    private void setTabLayout(){
        //设置监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //改变Tab 状态
                (tab.getCustomView().findViewById(R.id.iv_tab)).setSelected(true);
                (tab.getCustomView().findViewById(R.id.tv_tab)).setSelected(true);
                mViewPager.setCurrentItem(tab.getPosition());
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
        for (int i = 0; i < mFragmentList.size(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mFunctionAdapter.setCustomView(i));
        }
        //打开指定界面
        mTabLayout.getTabAt(0).select();
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}

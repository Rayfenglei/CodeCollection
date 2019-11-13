package com.example.ray.codecollections.view.recycleractivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseActivity1;
import com.example.ray.codecollections.base.BasePresenter;
import com.example.ray.codecollections.view.functionactivity.searchbanner.SearchFragment;
import com.example.ray.codecollections.view.recycleractivity.moverecyclerfragment.MoveRecyclerFragment;
import com.example.ray.codecollections.view.recycleractivity.normalrecyclerfragment.NormalRecyclerFragment;
import com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment.RecyclerFragment;
import com.example.ray.codecollections.view.recycleractivity.stickyrecyclerfragment.StickyRecyclerFragment;

import java.util.ArrayList;
import java.util.List;

public class NavigationRecyclerActivity extends BaseActivity1 {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private NavigationRecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initView();
        initData();
        initEvent();
        setTabLayout();
    }

    @Override
    public void initView() {
        mTabLayout = findViewById(R.id.tablayout_navigation);
        mViewPager = findViewById(R.id.viewpager_navigation);
    }

    @Override
    public void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new NormalRecyclerFragment());
        mFragmentList.add(new RecyclerFragment());
        mFragmentList.add(new StickyRecyclerFragment());
        mFragmentList.add(new MoveRecyclerFragment());

        mAdapter = new NavigationRecyclerAdapter(getSupportFragmentManager(),mFragmentList,this);
        mViewPager.setAdapter(mAdapter);
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
            tab.setCustomView(mAdapter.setCustomView(i));
        }
        //打开指定界面
        mTabLayout.getTabAt(0).select();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}

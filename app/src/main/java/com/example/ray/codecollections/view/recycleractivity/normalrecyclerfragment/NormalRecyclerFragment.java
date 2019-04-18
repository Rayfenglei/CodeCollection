package com.example.ray.codecollections.view.recycleractivity.normalrecyclerfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment;
import com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment.RecyclerBean;
import java.util.ArrayList;

public class NormalRecyclerFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private ArrayList<RecyclerBean> mDatas;
    private NormalRecyclerAdapter mAdapter;
    private ItemTouchHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_normal_recycler);

        initView();
        initData();
        initEvent();

        return view;
    }

    @Override
    public void initView() {
        mRecyclerView = view.findViewById(R.id.rv_normal_fragment);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        RecyclerBean bean = new RecyclerBean();
        bean.setTitle("this is recycle item");
        bean.setImageSource(R.mipmap.recycler_img1);
        for (int i = 0; i < 20; i++) {
            mDatas.add(bean);
        }
        //表格布局 spancount 几行或者几列
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        //滚动方向 水平或者垂直
        //gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //瀑布流布局
        // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NormalRecyclerAdapter(context, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //helper = new ItemTouchHelper(new QuickItemTouchCallback<NormalRecyclerAdapter, ArrayList>(mAdapter, mDatas));
        //helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void initEvent() {


    }
}
package com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment;

import java.util.ArrayList;

public class RecyclerFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private ArrayList<RecyclerBean> mDatas;
    private RecyclerAdapter mAdapter;
    private HeadFootAdapter mHeadFootAdapter;
    private ItemTouchHelper helper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_recycler);

        initView();
        initData();
        initEvent();

        return view;
    }

    @Override
    public void initView() {
        mRecyclerView = view.findViewById(R.id.rv_fragment);


    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        RecyclerBean bean = new RecyclerBean();
        bean.setTitle("this is recycle item");
        bean.setImageSource(R.mipmap.recycler_img1);
        for (int i = 0;i < 20; i++){
            mDatas.add(bean);
        }
        //表格布局 spancount 几行或者几列
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        //滚动方向 水平或者垂直
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //瀑布流布局
        // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecyclerAdapter(context,mDatas);

        //方法一添加头部和尾部
        mHeadFootAdapter = new HeadFootAdapter(mAdapter);
        View headView = LayoutInflater.from(context).inflate(R.layout.rv_header_item,mRecyclerView,false);
        View footView = LayoutInflater.from(context).inflate(R.layout.rv_footer_item,mRecyclerView,false);
        mHeadFootAdapter.addFooterView(footView);
        mHeadFootAdapter.addHeaderView(headView);
        mRecyclerView.setAdapter(mHeadFootAdapter);
        //方法二 直接根据位置添加头部和尾部
        helper = new ItemTouchHelper(new QuickItemTouchCallback<HeadFootAdapter,ArrayList>(mHeadFootAdapter,mDatas));
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void initEvent() {

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                showToast("onItenmClick:"+mRecyclerView.getChildLayoutPosition(view));
            }
        });

        mAdapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view) {
                showToast("onItemLongClick:"+mRecyclerView.getChildLayoutPosition(view));
            }
        });
    }

}

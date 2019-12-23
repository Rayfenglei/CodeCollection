package com.example.ray.codecollections.view.recycleractivity.normalrecyclerfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;
import com.example.ray.codecollections.util.LogUtil;
import com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment.RecyclerBean;
import java.util.ArrayList;

public class NormalRecyclerFragment extends BaseFragment1 {
    private RecyclerView mRecyclerRight,mRecyclerLeft;
    private ArrayList<RecyclerBean> mRightItemList,mLeftItemList;
    private NormalRecyclerAdapter mRightRecyclerAdapter;
    private LeftRecyclerAdapter mLeftRecyclerAdapter;
    private int mLeftClickPosition = 0;
    private LinearLayoutManager mLayoutManager;
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
        mRecyclerRight = view.findViewById(R.id.rv_normal_fragment);
        mRecyclerLeft = view.findViewById(R.id.rv_normal_left);
    }

    @Override
    public void initData() {
        mRightItemList = new ArrayList<>();

        mLeftItemList = new ArrayList<>();
        RecyclerBean bean;
        for (int i = 0; i < 6; i++) {
            bean = new RecyclerBean();
            bean.setTitle("item"+i);
            bean.setSelect(false);
            mLeftItemList.add(bean);
        }
        mLeftItemList.get(0).setSelect(true);
        mRecyclerLeft.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerLeft.setItemAnimator(new DefaultItemAnimator());
        mLeftRecyclerAdapter = new LeftRecyclerAdapter(context, mLeftItemList);
        mRecyclerLeft.setAdapter(mLeftRecyclerAdapter);
        //表格布局 spancount 几行或者几列
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        //滚动方向 水平或者垂直
        //gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //瀑布流布局
        // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //mRecyclerRight.setLayoutManager(gridLayoutManager);

        addRightAllData();
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerRight.setLayoutManager(mLayoutManager);
        mRecyclerRight.setItemAnimator(new DefaultItemAnimator());
        mRightRecyclerAdapter = new NormalRecyclerAdapter(context, mRightItemList);
        mRecyclerRight.setAdapter(mRightRecyclerAdapter);
        //helper = new ItemTouchHelper(new QuickItemTouchCallback<NormalRecyclerAdapter, ArrayList>(mRightRecyclerAdapter, mRightItemList));
        //helper.attachToRecyclerView(mRecyclerRight);
    }

    @Override
    public void initEvent() {
        mLeftRecyclerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (mLeftClickPosition != position){
                    mLeftItemList.get(mLeftClickPosition).setSelect(false);
                    mLeftItemList.get(position).setSelect(true);
                    mLeftRecyclerAdapter.notifyDataSetChanged();
                    //设置右侧列表数据
//                    mLeftClickPosition = position;
//                    mRightRecyclerAdapter.notifyDataSetChanged();
//                    addRightData(position);

                    //定位右侧需要展示数据的位置
                    int rightPosition = 0;
                    for (int i = 0; i < mRightItemList.size(); i++) {
                        if (mRightItemList.get(i).getType() != position){
                            rightPosition++;
                        }else {
                            break;
                        }
                    }
                    TopSmoothScroller s1 = new TopSmoothScroller(context);
                    s1.setTargetPosition(rightPosition);
                    mLayoutManager.startSmoothScroll(s1);
                }
            }
        });
        mRecyclerRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    /*
    * 列表联动滑动定位 数据全部加载
    * */
    private void addRightAllData(){
        RecyclerBean bean ;

        for (int i = 0; i < 6; i++) {
            for (int j =0; j < 10; j++){
                bean = new RecyclerBean();
                bean.setTitle("this is "+i);
                bean.setType(i);
                bean.setImageSource(R.mipmap.recycler_img1);
                mRightItemList.add(bean);
            }
        }
    }
    /*
    * 列表联动非全部加载滑动定位
    * 根据左侧添加右侧数据
    * */
    private void addRightData(int leftPosition){
        mRightItemList.clear();
        RecyclerBean bean ;
        switch (leftPosition){
            case 0:
                for (int i = 0; i < 10; i++) {
                    bean = new RecyclerBean();
                    bean.setTitle("this is 1");
                    bean.setImageSource(R.mipmap.recycler_img1);
                    mRightItemList.add(bean);
                }
                break;
            case 1:
                for (int i = 0; i < 10; i++) {
                    bean = new RecyclerBean();
                    bean.setTitle("this is 2");
                    bean.setImageSource(R.mipmap.recycler_img2);
                    mRightItemList.add(bean);
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    bean = new RecyclerBean();
                    bean.setTitle("this is 3");
                    bean.setImageSource(R.mipmap.recycler_img3);
                    mRightItemList.add(bean);
                }
                break;
            case 3:
                for (int i = 0; i < 10; i++) {
                    bean = new RecyclerBean();
                    bean.setTitle("this is 4");
                    bean.setImageSource(R.mipmap.recycler_img1);
                    mRightItemList.add(bean);
                }
                break;
            case 4:
                for (int i = 0; i < 10; i++) {
                    bean = new RecyclerBean();
                    bean.setTitle("this is 5");
                    bean.setImageSource(R.mipmap.recycler_img2);
                    mRightItemList.add(bean);
                }
                break;
            case 5:
                for (int i = 0; i < 10; i++) {
                    bean = new RecyclerBean();
                    bean.setTitle("this is 6");
                    bean.setImageSource(R.mipmap.recycler_img3);
                    mRightItemList.add(bean);
                }
                break;
        }
    }
    public class TopSmoothScroller extends LinearSmoothScroller {
        TopSmoothScroller(Context context) {
            super(context);
        }
        @Override
        protected int getHorizontalSnapPreference() {
            return SNAP_TO_START;//具体见源码注释
        }
        @Override
        protected int getVerticalSnapPreference() {
            return SNAP_TO_START;//具体见源码注释
        }
    }

}
package com.example.ray.codecollections.view.recycleractivity.stickyrecyclerfragment;

import android.graphics.Color;
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
import android.widget.TextView;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;
import com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment.QuickItemTouchCallback;
import com.example.ray.collection.Sticky.library.PowerfulStickyDecoration;
import com.example.ray.collection.Sticky.library.StickyDecoration;
import com.example.ray.collection.Sticky.library.listener.GroupListener;
import com.example.ray.collection.Sticky.library.listener.OnGroupClickListener;
import com.example.ray.collection.Sticky.library.listener.PowerGroupListener;
import com.example.ray.collection.Sticky.library.util.DensityUtil;

import java.util.ArrayList;

public class StickyRecyclerFragment extends BaseFragment1 {
    private StickyRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<StickyRecyclerBean> mDatas;
    private ItemTouchHelper mHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_sticky_recycler);
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void initView() {
        mRecyclerView = view.findViewById(R.id.rv_sticky_fragment);
    }

    @Override
    public void initData() {
        getCityList();
        ImageSticky();
    }

    @Override
    public void initEvent() {

    }
    /*
     * 自定义悬浮Item
     * */
    private void ImageSticky(){

        //------------- PowerfulStickyDecoration 使用部分  ----------------
        PowerGroupListener listener = new PowerGroupListener() {
            @Override
            public String getGroupName(int position) {
                //获取组名，用于判断是否是同一组
                if (mDatas.size() > position && position > -1) {
                    return mDatas.get(position).getProvince();
                }
                return null;
            }

            @Override
            public View getGroupView(int position) {
                //获取自定定义的组View
                if (mDatas.size() > position && position > -1) {
                    View view = getLayoutInflater().inflate(R.layout.define_sticky_item, null, false); //R.layout.item_group 自定义布局
                    ((TextView) view.findViewById(R.id.image_sticky_tv)).setText(mDatas.get(position).getProvince());
                    return view;
                } else {
                    return null;
                }
            }
        };
        PowerfulStickyDecoration decoration = PowerfulStickyDecoration.Builder
                .init(listener)
                .setGroupHeight(DensityUtil.dip2px(context, 40))     //设置高度
                .setGroupBackground(Color.parseColor("#48BDFF"))        //设置背景
                .setDivideColor(Color.parseColor("#CCCCCC"))            //分割线颜色
                .setDivideHeight(DensityUtil.dip2px(context, 1))     //分割线高度
                .setCacheEnable(true)                                              //是否使用缓存
                // .setHeaderCount(0)                                                  //头部数量
                .setOnClickListener(new OnGroupClickListener() {                   //点击事件，返回当前分组下的第一个item的position
                    @Override
                    public void onClick(int position, int id) {                                 //Group点击事件
                        String content = "onGroupClick --> " + mDatas.get(position).getProvince() + "   id --> " + id;
                        showToast(content);
                    }
                })
                .build();
        //-------------PowerfulStickyDecoration----------------
        //RV布局管理
//        RecyclerView.LayoutManager manager;
//
//        网格布局时需要这样使用
//        manager = new GridLayoutManager(this, 3);
//        decoration.resetSpan(mRv, (GridLayoutManager) manager);
//        mRv.setLayoutManager(manager);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //Item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(decoration);
        mAdapter = new StickyRecyclerAdapter(context,mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mHelper = new ItemTouchHelper(new QuickItemTouchCallback<StickyRecyclerAdapter,ArrayList>(mAdapter,mDatas));
        mHelper.attachToRecyclerView(mRecyclerView);

    }
    /*
     * 文字悬浮Item
     * */
    private void TextSticky(){
        //------------- StickyDecoration 使用部分  ----------------
        StickyDecoration decoration = StickyDecoration.Builder
                .init(new GroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //组名回调
                        if (mDatas.size() > position && position > -1) {
                            //获取组名，用于判断是否是同一组
                            return mDatas.get(position).getProvince();
                        }
                        return null;
                    }
                })
                .setGroupBackground(Color.parseColor("#48BDFF"))        //背景色
                .setGroupHeight(DensityUtil.dip2px(context, 35))     //高度
                .setDivideColor(Color.parseColor("#EE96BC"))            //分割线颜色
                .setDivideHeight(DensityUtil.dip2px(context, 2))     //分割线高度 (默认没有分割线)
                .setGroupTextColor(Color.BLACK)                                    //字体颜色 （默认）
                .setGroupTextSize(DensityUtil.sp2px(context, 15))    //字体大小
                .setTextSideMargin(DensityUtil.dip2px(context, 10))  // 边距   靠左时为左边距  靠右时为右边距
                //.setHeaderCount(2)                                             // header数量（默认0）
                .setOnClickListener(new OnGroupClickListener() {                   //点击事件，返回当前分组下的第一个item的position
                    @Override
                    public void onClick(int position, int id) {                                 //Group点击事件
                        String content = "onGroupClick --> " + mDatas.get(position).getProvince() ;
                        showToast(content);
                    }
                })
                .build();
        //------------- StickyDecoration 使用部分  ----------------
        //RV布局管理
//        RecyclerView.LayoutManager manager;
//
//        网格布局时需要这样使用
//        manager = new GridLayoutManager(this, 3);
//        decoration.resetSpan(mRv, (GridLayoutManager) manager);
//        mRv.setLayoutManager(manager);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //Item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(decoration);
        mAdapter = new StickyRecyclerAdapter(context,mDatas);
        mRecyclerView.setAdapter(mAdapter);

    }
    public void getCityList() {
        String[] CITYS = {"福建省", "安徽省", "浙江省", "江苏省"};
        mDatas = new ArrayList<>();
        final String FU_JIAN = CITYS[0];
        mDatas.add(new StickyRecyclerBean("福州", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("厦门", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("泉州", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("宁德", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("漳州", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("福州", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("厦门", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("泉州", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("宁德", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("漳州", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("福州", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("厦门", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("泉州", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("宁德", FU_JIAN));
        mDatas.add(new StickyRecyclerBean("漳州", FU_JIAN));
        final String AN_HUI = CITYS[1];
        mDatas.add(new StickyRecyclerBean("合肥", AN_HUI));
        mDatas.add(new StickyRecyclerBean("芜湖", AN_HUI));
        mDatas.add(new StickyRecyclerBean("蚌埠", AN_HUI));
        mDatas.add(new StickyRecyclerBean("合肥", AN_HUI));
        mDatas.add(new StickyRecyclerBean("芜湖", AN_HUI));
        mDatas.add(new StickyRecyclerBean("蚌埠", AN_HUI));
        final String ZHE_JIANG = CITYS[2];
        mDatas.add(new StickyRecyclerBean("杭州", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("宁波", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("温州", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("嘉兴", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("绍兴", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("金华", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("湖州", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("舟山", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("杭州", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("宁波", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("温州", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("嘉兴", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("绍兴", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("金华", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("湖州", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("舟山", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("杭州", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("宁波", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("温州", ZHE_JIANG));
        mDatas.add(new StickyRecyclerBean("嘉兴", ZHE_JIANG));
        final String JIANG_SU = CITYS[3];
        mDatas.add(new StickyRecyclerBean("南京", JIANG_SU));
        mDatas.add(new StickyRecyclerBean("南京", JIANG_SU));
        mDatas.add(new StickyRecyclerBean("南京", JIANG_SU));
        mDatas.add(new StickyRecyclerBean("南京", JIANG_SU));

    }
}

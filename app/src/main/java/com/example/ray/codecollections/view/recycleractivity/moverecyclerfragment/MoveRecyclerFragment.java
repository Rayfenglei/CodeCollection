package com.example.ray.codecollections.view.recycleractivity.moverecyclerfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;


import java.util.ArrayList;

public class MoveRecyclerFragment extends BaseFragment1 {

    private ArrayList<RecyclerMoveItemBean> list;
    private RecyclerView mRecyclerView;
    private RecyclerMoveAdapter mAdapter;
    private ItemTouchHelper helper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         setContentView(R.layout.fragment_move_recycler);
         initView();
         initData();
         return view;
    }

    @Override
    public void initView() {
        mRecyclerView = view.findViewById(R.id.rv_move_fragment);



    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        addDate();

        GridLayoutManager manager = new GridLayoutManager(context, 4);
        mRecyclerView.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mAdapter.getItemViewType(position);
                return viewType == RecyclerMoveAdapter.TYPE_MY || viewType == RecyclerMoveAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        helper = new ItemTouchHelper(new RecyclerMoveItemTouchCallback());
        helper.attachToRecyclerView(mRecyclerView);
        mAdapter = new RecyclerMoveAdapter(context, helper, list, new ArrayList<RecyclerMoveItemBean>());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addDate(){

        list = new ArrayList<>();
        RecyclerMoveItemBean bean1 = new RecyclerMoveItemBean("推荐");
        RecyclerMoveItemBean bean2 = new RecyclerMoveItemBean("新闻");
        RecyclerMoveItemBean bean3 = new RecyclerMoveItemBean("影视");
        RecyclerMoveItemBean bean4 = new RecyclerMoveItemBean("电视");
        RecyclerMoveItemBean bean5 = new RecyclerMoveItemBean("热点");
        RecyclerMoveItemBean bean6 = new RecyclerMoveItemBean("体育");
        RecyclerMoveItemBean bean7 = new RecyclerMoveItemBean("娱乐");
        RecyclerMoveItemBean bean8 = new RecyclerMoveItemBean("音乐");
        RecyclerMoveItemBean bean9 = new RecyclerMoveItemBean("电影");

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);
        list.add(bean9);
    }


}

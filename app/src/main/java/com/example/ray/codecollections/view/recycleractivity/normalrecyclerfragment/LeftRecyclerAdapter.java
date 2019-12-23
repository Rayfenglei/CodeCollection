package com.example.ray.codecollections.view.recycleractivity.normalrecyclerfragment;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ray.codecollections.R;
import com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment.RecyclerBean;

import java.util.ArrayList;

public class LeftRecyclerAdapter extends BaseQuickAdapter<RecyclerBean, BaseViewHolder> {
    private ArrayList<RecyclerBean> mRecyclerBeans;
    private Context mContext;

    public LeftRecyclerAdapter(Context mContext, ArrayList<RecyclerBean> mRecyclerBeans) {
        super(R.layout.recycler_item, mRecyclerBeans);
        this.mRecyclerBeans = mRecyclerBeans;
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RecyclerBean item) {
        Log.i("fengl", item.getTitle() +" "+item.isSelect());
        helper.setText(R.id.tv_fg_recycler, item.getTitle())
                .addOnClickListener(R.id.tv_fg_recycler)
                .setBackgroundColor(R.id.ll_recycler_normal, item.isSelect() ? Color.GREEN : Color.WHITE);
    }
}

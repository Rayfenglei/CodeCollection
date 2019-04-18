package com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;

/*
*  RecyclerView 拖拽和侧滑
*
*
* @ T RecyclerView 的 Adapter
* @ V ArrayList 的 数据
* 使用
* helper = new ItemTouchHelper(new QuickItemTouchCallback<HeadFootAdapter,ArrayList>(mHeadFootAdapter,mDatas));
*
* */

public class QuickItemTouchCallback<T extends RecyclerView.Adapter<RecyclerView.ViewHolder>,V extends ArrayList>  extends ItemTouchHelper.Callback {
    private T mAdapter;
    private V mData;

    public QuickItemTouchCallback(T mAdapter, V mData) {
        this.mAdapter = mAdapter;
        this.mData = mData;
    }

    /*
     * 滑动方向 和 拖拽方向
     * */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlag;
        //根据布局 设置拖拽方向
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        int swipeFlag = 0;
        //设置左右滑动
        //swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    /*
     * 移动
     * */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();
        if (from == 0) {
            return true;
        }
        Collections.swap(mData, from, to);
        mAdapter.notifyItemMoved(from, to);
        return true;
    }

    /*
     * 侧滑删除
     * */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//        int pos = viewHolder.getAdapterPosition();
//        mData.remove(pos);
//        mAdapter.notifyItemRemoved(pos);
    }

    /*
     * 选择时的颜色
     * */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(0xffbcbcbc); //设置拖拽和侧滑时的背景色
        }
    }

    /*
     *拖拽或滑动完成之后调用，用来清除一些状态
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0xfffffff); //背景色还原
    }
}
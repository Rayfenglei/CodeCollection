package com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ray.codecollections.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<RecyclerBean> mDatas = new ArrayList<>();

    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public RecyclerAdapter(Context mContext, ArrayList<RecyclerBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerBean bean = mDatas.get(position);
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.imageView.setImageResource(bean.getImageSource());
        holder.textView.setText(bean.getTitle());
        ////对RecyclerView的每一个itemView设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemLongClickListener.onItemLongClick(view);
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.tv_fg_recycler);
            imageView = view.findViewById(R.id.im_fg_recycler);
        }
    }
    //  定义点击回调接口
    public interface OnItemClickListener {
        void onItemClick(View view);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view);
    }
    //  定义一个设置点击监听器的方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.itemLongClickListener = onItemLongClickListener;
    }
}

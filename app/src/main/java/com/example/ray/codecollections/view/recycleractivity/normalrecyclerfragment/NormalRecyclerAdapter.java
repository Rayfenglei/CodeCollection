package com.example.ray.codecollections.view.recycleractivity.normalrecyclerfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment.RecyclerBean;

import java.util.ArrayList;

public class NormalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEAD = 0;
    public static final int TYPE_FOOT = 1;
    public static final int TYPE_CONTENT = 2;


    private Context mContext;
    private ArrayList<RecyclerBean> mDatas = new ArrayList<>();

    public NormalRecyclerAdapter(Context mContext, ArrayList<RecyclerBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    public class ContentVH extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        ContentVH(View view){
            super(view);
            textView = view.findViewById(R.id.tv_fg_recycler);
            imageView = view.findViewById(R.id.im_fg_recycler);
        }
    }

    public class HeaderVH extends RecyclerView.ViewHolder{
        TextView textView;
        HeaderVH(View view){
            super(view);
            textView = view.findViewById(R.id.tv_rv_item_header);
        }
    }
    public class FooterVH extends RecyclerView.ViewHolder{
        TextView textView;
        FooterVH(View view){
            super(view);
            textView = view.findViewById(R.id.tv_rv_item_footer);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_HEAD;
        }else if(position == mDatas.size()+1) {
            return TYPE_FOOT;
        }else {
            return TYPE_CONTENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType){
            case TYPE_HEAD:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_header_item,viewGroup,false);
                return new HeaderVH(view);
            case TYPE_FOOT:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_footer_item,viewGroup,false);
                return new FooterVH(view);
            case TYPE_CONTENT:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);
                return new ContentVH(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof HeaderVH){
            HeaderVH headerVH = (HeaderVH) viewHolder;
            headerVH.textView.setText(R.string.rv_header);
        }else if(viewHolder instanceof FooterVH){
            FooterVH footerVH = (FooterVH) viewHolder;
            footerVH.textView.setText(R.string.rv_footer);
        }else if(viewHolder instanceof ContentVH){
            RecyclerBean bean = mDatas.get(position-1);
            ContentVH contentVH = (ContentVH) viewHolder;
            contentVH.imageView.setImageResource(bean.getImageSource());
            contentVH.textView.setText(bean.getTitle());
            //添加点击事件
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size()+2;
    }
}

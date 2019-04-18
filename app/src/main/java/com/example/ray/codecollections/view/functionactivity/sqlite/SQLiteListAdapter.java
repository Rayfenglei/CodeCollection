package com.example.ray.codecollections.view.functionactivity.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ray.codecollections.R;

import java.util.List;


public class SQLiteListAdapter extends BaseAdapter{
    private Context context;
    private List<SQLiteBean> SQLiteBeanList;

    public SQLiteListAdapter(Context context, List<SQLiteBean> SQLiteBeanList) {
        this.context = context;
        this.SQLiteBeanList = SQLiteBeanList;
    }

    @Override
    public int getCount() {
        return SQLiteBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return SQLiteBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        SQLiteBean SQLiteBean = SQLiteBeanList.get(position);
        if (SQLiteBean == null){
            return null;
        }

        ViewHolder holder = null;
        if (view != null){
            holder = (ViewHolder) view.getTag();
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.sql_item, null);

            holder = new ViewHolder();
            holder.dateIdTextView =  view.findViewById(R.id.dateIdTextView);
            holder.dateCustomTextView =  view.findViewById(R.id.dateCustomTextView);
            holder.dateOrderPriceTextView =  view.findViewById(R.id.dateOrderPriceTextView);
            holder.dateCountoryTextView =  view.findViewById(R.id.dateCountoryTextView);

            view.setTag(holder);
        }

        holder.dateIdTextView.setText(SQLiteBean.id + "");
        holder.dateCustomTextView.setText(SQLiteBean.customName);
        holder.dateOrderPriceTextView.setText(SQLiteBean.orderPrice + "");
        holder.dateCountoryTextView.setText(SQLiteBean.country);

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public static class ViewHolder{
        public TextView dateIdTextView;
        public TextView dateCustomTextView;
        public TextView dateOrderPriceTextView;
        public TextView dateCountoryTextView;
    }
}

package com.example.ray.codecollections.view.functionactivity.searchbanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ray.codecollections.R;

import java.util.ArrayList;
import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> listHistoty;
    public SearchHistoryAdapter(Context context,List<String> listHistoty) {
        super();
        this.context = context;
        this.listHistoty = listHistoty;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.search_item, viewGroup, false);
        return new SearchHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SearchHistoryHolder holder = (SearchHistoryHolder) viewHolder;
        holder.textView.setText(listHistoty.get(i));
    }

    @Override
    public int getItemCount() {
        return listHistoty.size();
    }

    public class SearchHistoryHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public SearchHistoryHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tv_search_item);
        }
    }

    public void notifyDataClearChanged(){
        listHistoty.clear();
        notifyDataSetChanged();
    }
    public void addDataChanged(String s){
        listHistoty.add(s);
        notifyDataSetChanged();
    }
}

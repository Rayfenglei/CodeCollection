//package com.example.ray.codecollections.base;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//
//import java.util.List;
//
//public class DemoAdapter extends BaseRecyclerAdapter<HistoryBean> {
//
//    private List<HistoryBean> dataList;
//    private Context context;
//
//    public DemoAdapter(Context context, List<HistoryBean> dataList) {
//        super(context, dataList, -1);
//        this.dataList = dataList;
//        this.context = context;
//    }
//
//    @Override
//    protected void bindData(final BaseViewHolder holder, final HistoryBean data) {
//        if (holder instanceof NormalHolder) {
//            holder.setText(R.id.tv_name, data.getTitle());
//            final CheckBox checkBox = holder.getView(R.id.checkbox);
//            if (data.showCheckbox) {
//                holder.setViewVisibility(R.id.checkbox, View.VISIBLE);
//            } else {
//                holder.setViewVisibility(R.id.checkbox, View.GONE);
//            }
//            if(data.isShowLine()){
//                holder.setViewVisibility(R.id.line,View.VISIBLE);
//            }else{
//                holder.setViewVisibility(R.id.line,View.GONE);
//            }
//            if (data.isCheck()) {
//                checkBox.setChecked(true);
//            } else {
//                checkBox.setChecked(false);
//            }
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    OnItemClickListener itemClickListener = getItemClickListener();
//                    if (null != itemClickListener) {
//                        itemClickListener.onItemClick(data, holder.getAdapterPosition(), holder.itemView);
//                    }
//                }
//            });
//        } else if (holder instanceof DateHolder) {
//            holder.setText(R.id.tv_date, data.getDate());
//        }
//    }
//
//    @Override
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view;
//        BaseViewHolder holder = null;
//        switch (viewType) {
//            case BrowserConstants.HISTORY.TYPE_LIST_NORMAL_ITEM:
//                view = LayoutInflater.from(context).inflate(R.layout.view_history_list_normal_item, null);
//                holder = new NormalHolder(view);
//                break;
//            case BrowserConstants.HISTORY.TYPE_LIST_DATE_ITEM:
//                view = LayoutInflater.from(context).inflate(R.layout.view_history_list_date_item, null);
//                holder = new DateHolder(view);
//                break;
//        }
//        return holder;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (dataList != null && dataList.size() > 0) {
//            return dataList.get(position).getType();
//        } else {
//            return -1;
//        }
//    }
//
//    public boolean isAllNotSelect() {
//        List<HistoryBean> dataList = getDataList();
//        for (int i = 0; i < dataList.size(); i++) {
//            if (dataList.get(i).isCheck()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private class NormalHolder extends BaseViewHolder {
//        public NormalHolder(View itemView) {
//            super(itemView);
//        }
//    }
//
//    private class DateHolder extends BaseViewHolder {
//
//        public DateHolder(View itemView) {
//            super(itemView);
//        }
//    }
//}

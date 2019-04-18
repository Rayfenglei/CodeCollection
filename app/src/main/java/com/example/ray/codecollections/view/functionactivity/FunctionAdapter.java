package com.example.ray.codecollections.view.functionactivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ray.codecollections.R;

import java.util.List;

public class FunctionAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private Context mContext;

    public FunctionAdapter(FragmentManager fm, List<Fragment> mList, Context mContext) {
        super(fm);
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mList == null ? null : this.mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
    //自定义导航栏
    public View setCustomView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.navigation_define_tab,null);
        TextView tv = view.findViewById(R.id.tv_tab);
        ImageView iv = view.findViewById(R.id.iv_tab);
        switch (position){
            case 0:
                iv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.tab_define_strategy_selector,null));
                tv.setText("Down");
                break;
            case 1:
                iv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.tab_define_food_selector,null));
                tv.setText("Net");
                break;
            case 2:
                iv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.tab_define_track_selector,null));
                tv.setText("Search");
                break;
            case 3:
                iv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.tab_define_my_selector,null));
                tv.setText("SQL");
                break;
        }
        return view;

    }
}

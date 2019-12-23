package com.example.ray.codecollections.view.BesselLineActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class BesselAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private Context mContext;

    public BesselAdapter(FragmentManager fm, List<Fragment> mList, Context mContext) {
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
}

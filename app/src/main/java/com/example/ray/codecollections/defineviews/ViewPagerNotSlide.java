package com.example.ray.codecollections.defineviews;

import android.content.Context;

import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerNotSlide extends ViewPager {
    public ViewPagerNotSlide( Context context) {
        super(context);
    }

    public ViewPagerNotSlide( Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /*
    * 拦截手势 禁止左右滑动
    * */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}

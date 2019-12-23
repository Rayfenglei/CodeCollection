package com.example.ray.codecollections.defineviews;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class LinesView extends View {
    private Path mPath;
    private Paint mPaint;
    private int dx;
    public LinesView(Context context) {
        super(context);
        init();
    }

    public LinesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(100,600);
        mPath.lineTo(400,100);
        mPath.lineTo(700,900);
        mPaint.setPathEffect(new DashPathEffect(new float[]{20,10,100,100},dx));
        canvas.drawPath(mPath,mPaint);
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
    }
    public void setAnimator(){
        ValueAnimator animator = ValueAnimator.ofInt(0, 230);
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        animator.setInterpolator(new LinearInterpolator());//插值器 以常量速率变化
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}

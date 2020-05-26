package com.example.ray.codecollections.defineviews;

import android.animation.ValueAnimator;
import android.app.IntentService;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.example.ray.codecollections.util.LogUtil;
/*
 * 轨迹动画
 * 重要API PathMeasure
 * */

public class PathAnimatorView extends View {
    private Path mPathLine;
    private Path mPathCircle;
    private Paint mPaint;
    private PathMeasure mPathMeasureCircle, mPathMeasureLine;
    private Path mDst;
    private float mLength;
    private float mAnimatorValue;
    private float[] pos;

    public PathAnimatorView(Context context) {
        super(context);
        init();
        setAnimatorValue();
    }

    public PathAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setAnimatorValue();
    }

    public PathAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setAnimatorValue();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPathLine, mPaint);
        mPathMeasureLine.getPosTan(mPathMeasureLine.getLength() * mAnimatorValue, pos, null);
        canvas.drawCircle(pos[0], pos[1], 20, mPaint);

        mDst.reset();
        // 硬件加速的BUG
        mDst.lineTo(0, 0);
        float stop = mLength * mAnimatorValue;
        //截取整个Path的片段 （改变起点有不同效果）
        mPathMeasureCircle.getSegment(0, stop, mDst, true);
        canvas.drawPath(mDst, mPaint);
    }

    private void init() {
        pos = new float[2];
        mPaint = new Paint();
        mPathLine = new Path();
        mPathCircle = new Path();
        mPathMeasureCircle = new PathMeasure();
        mPathMeasureLine = new PathMeasure();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        drawLine();
        drawCircle();
    }

    private void drawLine() {
        mPathLine.moveTo(200, 300);
        mPathLine.quadTo(300, 200, 400, 300);
        mPathLine.quadTo(500, 400, 600, 300);
        mPathLine.quadTo(700, 400, 600, 500);
        mPathLine.quadTo(500, 600, 600, 700);
        /*
         *  @param path 路径
         *  @param forceClosed 是否闭合
         * */
        mPathMeasureLine.setPath(mPathLine, false);
    }

    private void drawCircle() {
        mPathCircle.addCircle(400, 400, 300, Path.Direction.CW);
        mPathMeasureCircle.setPath(mPathCircle, true);
        //获取路径长度
        mLength = mPathMeasureCircle.getLength();
        mDst = new Path();
    }

    /*
     *   设置动画
     * */
    private void setAnimatorValue() {
        //设置0，1 相当于百分比插值器
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(4000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }
}

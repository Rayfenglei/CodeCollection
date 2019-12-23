package com.example.ray.codecollections.defineviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BesselQaudtoView extends View {
    private Paint mPaint;
    private Path mPath;
    private float mPreX, mPreY;

    public BesselQaudtoView(Context context) {
        super(context);
        init();
    }

    public BesselQaudtoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BesselQaudtoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:  //需要设置clickable 为 true
                float endX = (mPreX + event.getX()) / 2; //把各个线段的中间点做为起始点和终点，把终点前一个手指位置做为控制点
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }
}

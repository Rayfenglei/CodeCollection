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

public class BesselView extends View {
    private Paint mPaint;
    private Path mPath;

    public BesselView(Context context) {
        super(context);
        init();
    }

    public BesselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BesselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                Log.i("BesselView", "  ACTION_DOWN getX = " + event.getX() + " getY = " + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:  //需要设置clickable 为 true
                Log.i("BesselView", "  ACTION_MOVE getX = " + event.getX() + " getY = " + event.getY());
                mPath.lineTo(event.getX(),event.getY());
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

    public void reset(){
        mPath.reset();
        invalidate();
    }
}

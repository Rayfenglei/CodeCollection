package com.example.ray.codecollections.defineviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.ray.codecollections.R;

public class BookPageView extends View {
    private Bitmap mBitmap;
    private int width,height;
    private Paint mPaint;
    private Context context;
    public BookPageView(Context context) {
        super(context);
        init();
    }

    public BookPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BookPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = Bitmap.createScaledBitmap(mBitmap,width,height,true);
        canvas.drawBitmap(bitmap,0,0,null);
    }
    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mBitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.recycler_big_img3,null)).getBitmap();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                doAction(event.getX());
                break;
        }
        return super.onTouchEvent(event);
    }
    private void doAction(float x){
        if (x > width/2){
            mBitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.recycler_big_img2,null)).getBitmap();
            invalidate();
        }else {
            mBitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.recycler_big_img1,null)).getBitmap();
            invalidate();
        }

    }
}

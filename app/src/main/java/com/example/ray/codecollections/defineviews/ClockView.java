package com.example.ray.codecollections.defineviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.ray.codecollections.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class ClockView extends View {

    //    圆形和刻度的画笔、指针的画笔、数字的画笔
    private Paint mCirclePaint, mPointerPaint, mNumPaint;
    //    时钟的外环宽度、时钟的半径、默认刻度的宽度、默认刻度的长度
    //    特殊刻度的宽度、特殊刻度的长度、时针的宽度、分针的宽度、秒针的宽度、数字尺寸
    private float mClockRingWidth, mClockRadius, mDefaultWidth, mDefaultLength,
            mSpecialWidth, mSpecialLength, mHourWidth, mMinuteWidth, mSecondWidth, mNumSize;
    //    圆形和刻度的颜色，时针的颜色，分针的颜色，秒针的颜色，数字的颜色
    private int mCircleColor, mHourColor, mMinuteColor, mSecondColor, mNumColor;

    private int mWidth, mHeight, mCenterX, mCenterY;
    private float mHour, mMinute, mSecond;

    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (mSecond == 360) {
                mSecond = 0;
            }
            if (mMinute == 60) {
                mMinute = 0;
            }
            if (mHour == 12) {
                mHour = 0;
            }
            mSecond += 6;
            mMinute += 0.1f;
            mMinute += 1.0f / 120;
            postInvalidate();//子线程刷新界面
        }
    };

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        start();
        initPaint();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getWidthOrHeightSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = getWidthOrHeightSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /*
     *   在控件大小发生改变时调用。所以这里初始化会被调用一次
     *   获取控件的宽和高度
     * */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCenterX = w / 2;
        mCenterY = h / 2;
        mClockRadius = (float) ((float) (w / 2) * 0.8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动画布的坐标原点到中心位置
        canvas.translate(mCenterX, mCenterY);
        drawCircle(canvas);
        drawPaint(canvas);
        drawNumber(canvas);
    }

    /**
     * 画园和刻度
     */
    private void drawCircle(Canvas canvas) {
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(mClockRingWidth);
        //画圆环
        canvas.drawCircle(0, 0, mClockRadius, mCirclePaint);
        //画刻度
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {//特殊的刻度
                mCirclePaint.setStrokeWidth(mSpecialWidth);
                mCirclePaint.setColor(mHourColor);
                canvas.drawLine(0, -mClockRadius + mClockRingWidth / 2, 0, -mClockRadius + mSpecialLength, mCirclePaint);
            } else {//普通刻度
                mCirclePaint.setStrokeWidth(mDefaultWidth);
                mCirclePaint.setColor(mSecondColor);
                canvas.drawLine(0, -mClockRadius + mClockRingWidth / 2, 0, -mClockRadius + mDefaultLength, mCirclePaint);
            }
            //通过旋转画布的方式快速设置刻度
            canvas.rotate(6);
        }
    }

    /**
     * 画数字
     */
    private void drawNumber(Canvas canvas) {
        mNumPaint.setColor(mNumColor);
        mNumPaint.setTextSize(mNumSize);

        for (int i = 0; i < 12; i++) {
            //保存画布状态
            canvas.save();
            canvas.translate(0, (-mClockRadius + mSpecialLength + mDefaultLength + mClockRingWidth));
            if (i == 0) {
                String text = "12";
                Rect bound = new Rect();
                //计算出绘制文字的最小宽度
                mNumPaint.getTextBounds(text, 0, text.length(), bound);
                canvas.drawText(text, -bound.width() / 2, bound.height() / 2, mNumPaint);
            } else {
                String number = i + "";
                Rect bound = new Rect();
                mNumPaint.getTextBounds(number, 0, number.length(), bound);
                canvas.rotate(-i * 30);//因画布被旋转了，所以要把画布正过来再绘制数字
                canvas.drawText(number, -bound.width() / 2, bound.height() / 2, mNumPaint);
            }
            //恢复画布状态
            canvas.restore();
            canvas.rotate(30);
        }
    }

    /**
     * 画时针
     */
    private void drawPaint(Canvas canvas) {
        canvas.save();
        //画时针
        mPointerPaint.setColor(mHourColor);
        mPointerPaint.setStrokeWidth(mHourWidth);
        /*
         *  canvas.rotate(degrees) 旋转画布 坐标原点不变
         *  canvas.rotate(degrees,x,x) 旋转画布，坐标原点为（x,y）
         * */
        canvas.rotate(mHour, 0, 0);
        canvas.drawLine(0, -20, 0, (float) (mClockRadius * 0.4), mPointerPaint);
        canvas.restore();

        canvas.save();
        //画分针
        mPointerPaint.setColor(mMinuteColor);
        mPointerPaint.setStrokeWidth(mMinuteWidth);
        canvas.rotate(mMinute, 0, 0);
        canvas.drawLine(0, -20, 0, (float) (mClockRadius * 0.5), mPointerPaint);
        canvas.restore();

        canvas.save();
        //画秒针
        mPointerPaint.setColor(mSecondColor);
        mPointerPaint.setStrokeWidth(mSecondWidth);
        canvas.rotate(mSecond, 0, 0);
        canvas.drawLine(0, -30, 0, (float) (mClockRadius * 0.6), mPointerPaint);
        canvas.restore();

        //画点
        mPointerPaint.setColor(mSecondColor);
        mPointerPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, mHourWidth, mPointerPaint);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setAntiAlias(true);//设置抗锯齿
        mCirclePaint.setStyle(Paint.Style.STROKE);//边框

        mPointerPaint = new Paint();
        mPointerPaint.setAntiAlias(true);//设置抗锯齿
        mPointerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPointerPaint.setStrokeCap(Paint.Cap.ROUND);//末端圆弧

        mNumPaint = new Paint();
        mNumPaint.setColor(mNumColor);
        mNumPaint.setAntiAlias(true);//设置抗锯齿
        mNumPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 获取xml设置的属性值
     * <p>
     * 自定义属性需要在attrs中定义
     **/
    private void init(Context context, AttributeSet attrs) {
        //mRadius 从xml属性中获取
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ClockView);
        mClockRingWidth = array.getDimension(R.styleable.ClockView_mClockRingWidth, dp2px(context, 4));
        mDefaultWidth = array.getDimension(R.styleable.ClockView_mDefaultWidth, dp2px(context, 1));
        mDefaultLength = array.getDimension(R.styleable.ClockView_mDefaultLength, dp2px(context, 6));
        mSpecialWidth = array.getDimension(R.styleable.ClockView_mSpecialWidth, dp2px(context, 2));
        mSpecialLength = array.getDimension(R.styleable.ClockView_mSpecialLength, dp2px(context, 11));
        mHourWidth = array.getDimension(R.styleable.ClockView_mHWidth, dp2px(context, 6));
        mMinuteWidth = array.getDimension(R.styleable.ClockView_mMWidth, dp2px(context, 4));
        mSecondWidth = array.getDimension(R.styleable.ClockView_mSWidth, dp2px(context, 2));
        mNumSize = array.getDimension(R.styleable.ClockView_mNumSize, sp2px(context, 10));
        mCircleColor = array.getColor(R.styleable.ClockView_mCircleColor, Color.RED);
        mHourColor = array.getColor(R.styleable.ClockView_mHColor, Color.BLACK);
        mMinuteColor = array.getColor(R.styleable.ClockView_mMColor, Color.BLACK);
        mSecondColor = array.getColor(R.styleable.ClockView_mSColor, Color.RED);
        mNumColor = array.getColor(R.styleable.ClockView_mNumColor, Color.BLACK);
        //记得使用完销毁
        array.recycle();

        Calendar mCalendar = Calendar.getInstance();
        //获取当前小时数
        int hours = mCalendar.get(Calendar.HOUR);
        //获取当前分钟数
        int minutes = mCalendar.get(Calendar.MINUTE);
        //获取当前秒数
        int seconds = mCalendar.get(Calendar.SECOND);
        setTime(hours, minutes, seconds);
    }

    /**
     * 获取width Height的值
     */
    private int getWidthOrHeightSize(int size, int measureSpec) {
        int result = size;
        //获取测量模式 和 实际数值
        //①UNSPECIFIED(未指定)--具体值，父元素部队自元素施加任何束缚，子元素可以得到任意想要的大小；
        //②EXACTLY(完全)------match_parent，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
        //③AT_MOST(至多)-----wrap_content，子元素至多达到指定大小的值。
        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result = specSize;
                break;
        }
        return result;
    }

    /**
     * 设置时分秒线的弧度
     */

    public void setTime(int h, int m, int s) {
        if (h >= 24 || h < 0 || m >= 60 || m < 0 || s >= 60 || s < 0) {
            Toast.makeText(getContext(), "时间不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        //需要以12点为准，所以统一减去180度
        float v = h + m * 1.0f / 60f + s * 1.0f / 3600f;
        if (h >= 12) {
            mHour = (v - 12) * 30f - 180;
        } else {
            mHour = v * 30f - 180;
        }
        mMinute = (m + s * 1.0f / 60f) * 6f - 180;
        mSecond = s * 6f - 180;
    }

    /**
     * 开启定时器
     */
    public void start() {
        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * dp转换成px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp转换成px
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}

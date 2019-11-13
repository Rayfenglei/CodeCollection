package com.example.ray.codecollections.view.scrollactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.defineviews.BesselView;
import com.example.ray.codecollections.defineviews.ClockView;

public class ScrollingActivity extends AppCompatActivity {
    private Button btScroll;
    private ClockView clockView;
    private EditText etHour, etMinute, etSecond;
    int lastX = 0;
    int lastY = 0;
    private GestureDetector gestureDetector;
    private GestureDetector.SimpleOnGestureListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        initView();
        initEvent();
        initData();
    }

    private void initData() {
        gestureDetector = new GestureDetector(this, listener);
    }

    private void initView() {
        btScroll = findViewById(R.id.bt_scroll);
        clockView = findViewById(R.id.clockview);
        etHour = findViewById(R.id.et_hour);
        etMinute = findViewById(R.id.et_minute);
        etSecond = findViewById(R.id.et_second);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
        btScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                int rawX = (int) event.getRawX();
//                int rawY = (int) event.getRawY();
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        //记录触摸点的坐标
//                        lastX = rawX;
//                        lastY = rawY;
//                        Log.i("ScrollingActivity","rawX = "+rawX+" rawY = "+rawY);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        //计算偏移量
//                        int officeX = rawX - lastX;
//                        int officeY = rawY - lastY;
//                        Log.i("ScrollingActivity","officeX = "+officeX+" officeY = "+officeY);
//                        //在当前的left,top,right,bottom基础上加上偏移量
//                        v.layout(v.getLeft()+officeX,v.getTop()+officeY,v.getRight()+officeX,v.getBottom()+officeY);
//                        //重新设置初始值
//                        lastX = rawX;
//                        lastY = rawY;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        //处理输入的离开动作
//                        break;
//                }
                return gestureDetector.onTouchEvent(event);
            }
        });
        listener = new GestureDetector.SimpleOnGestureListener() {
            int rawX = 0;
            int rawY = 0;

            /*
             * 用户按下屏幕就会触发
             * */
            @Override
            public boolean onDown(MotionEvent e) {
                rawX = (int) e.getRawX();
                rawY = (int) e.getRawY();
                Log.i("ScrollingActivity", "  onDown rawX = " + rawX + " rawY = " + rawY);
                return false;
            }

            /*
             * 点击
             * */
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                setClockViewTime();
                return true;
            }

            /**
             * 滑动
             *
             * */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                /*
                 * 实现移动控件
                 * */
                int officeX = (int) e2.getX();
                int officeY = (int) e2.getY();
                //在当前的left,top,right,bottom基础上加上偏移量
                btScroll.layout(btScroll.getLeft() + officeX-btScroll.getWidth()/2, btScroll.getTop() + officeY-btScroll.getHeight()/2,
                        btScroll.getRight() + officeX-btScroll.getWidth()/2, btScroll.getBottom() + officeY-btScroll.getHeight()/2);
                return true;
            }

        };
    }

    private void setClockViewTime() {
        int hour = Integer.valueOf(etHour.getText().toString());
        int minute = Integer.valueOf(etMinute.getText().toString());
        int second = Integer.valueOf(etSecond.getText().toString());
        Log.i("ScrollingActivity", " setClockViewTime  hour = " + hour + " minute = " + minute + " second = " + second);
        clockView.setTime(hour, minute, second);
    }
}

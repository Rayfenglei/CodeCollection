package com.example.ray.codecollections;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ray.codecollections.base.BaseActivity;
import com.example.ray.codecollections.base.BasePresenter;
import com.example.ray.codecollections.util.PermissionsUtil;
import com.example.ray.codecollections.view.MainActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity {
    private Handler handler = new mHandler(getBaseContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initView() {
        //检查权限
        if (PermissionsUtil.isPermissionGranted(this)) {
            startActivity(new Intent(this, MainActivity.class));

        } else {
            PermissionsUtil.requestPermissions(this);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionsUtil.REQUEST_CODE_PERMISSION) {
            if (PermissionsUtil.isPermissionGranted(this)) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                finish();
            }
        }
    }

    private static class mHandler extends Handler{
        private WeakReference<Context> ref;
        public mHandler(Context context){
            ref = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            Context context = ref.get();

            super.handleMessage(msg);
        }
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}

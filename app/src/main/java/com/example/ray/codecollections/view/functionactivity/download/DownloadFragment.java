package com.example.ray.codecollections.view.functionactivity.download;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment;

public class DownloadFragment extends BaseFragment implements View.OnClickListener{
    private Button btStart,btStop,btCancle;
    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection serviceConnection;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_download);
        initView();
        initData();
        initEvent();

        Intent intent = new Intent(context, DownloadService.class);
        context.startService(intent); // 启动服务
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE); // 绑定服务

        return view;
    }

    @Override
    public void initView() {
        btStart = view.findViewById(R.id.bt_download_start);
        btStop = view.findViewById(R.id.bt_download_stop);
        btCancle = view.findViewById(R.id.bt_download_cancel);
    }

    @Override
    public void initData() {
    /* ServiceConnection代表与服务的连接，它只有两个方法：onServiceConnected和onServiceDisconnected，
    前者是在操作者在连接一个服务成功时被调用，而后者是在服务崩溃或被杀死导致的连接中断时被调用 */
        serviceConnection = new ServiceConnection() {
            /* onServiceConnected 绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
            通过这个IBinder对象，实现宿主和Service的交互。 */
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                // 获取Binder
                downloadBinder = (DownloadService.DownloadBinder) iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

    }

    @Override
    public void initEvent() {
        btStart.setOnClickListener(this);
        btStop.setOnClickListener(this);
        btCancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (downloadBinder == null) {
            return;
        }
        switch (view.getId()){
            case R.id.bt_download_start:
                String url = "https://dldir1.qq.com/weixin/android/weixin672android1340.apk";
                downloadBinder.startDownload(url);
                break;
            case R.id.bt_download_stop:
                downloadBinder.stopDownload();
                break;
            case R.id.bt_download_cancel:
                downloadBinder.cancelDownload();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unbindService(serviceConnection);
    }
}

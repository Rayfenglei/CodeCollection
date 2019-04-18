package com.example.ray.codecollections.view.functionactivity.download;

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onStop();

    void onCanceled();
}

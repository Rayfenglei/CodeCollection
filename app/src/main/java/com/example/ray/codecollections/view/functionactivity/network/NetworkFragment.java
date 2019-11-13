package com.example.ray.codecollections.view.functionactivity.network;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkFragment extends BaseFragment1 {
    private TabLayout mTabLayout;
    private VideoModelImpl videoModel;
    private ArrayList<CategoryBean> categoryBeans = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_network);
        initView();
        initData();
        initEvent();
        getCategory();
        videoModel.getVideos();
        return view;
    }

    @Override
    public void initView() {
        mTabLayout = view.findViewById(R.id.tab_video_type);
    }

    @Override
    public void initData() {
        videoModel = new VideoModelImpl();
        //getCategory();
    }

    @Override
    public void initEvent() {

    }

    private void getCategory(){
        //获取视频类别
        Call<CategoryContent<List<CategoryBean>>> call = CallRetrofitService.getInstance().getRetrofitService().getVideoCategory();
        //进行网络请求
        call.enqueue(new Callback<CategoryContent<List<CategoryBean>>>() {
            @Override
            public void onResponse(Call<CategoryContent<List<CategoryBean>>> call, Response<CategoryContent<List<CategoryBean>>> response) {
                for (int i = 0; i<response.body().getResult().size(); i++){
                    CategoryBean bean = new CategoryBean();
                    bean.setAdTrack(response.body().getResult().get(i).getAdTrack());
                    bean.setApiUrl(response.body().getResult().get(i).getApiUrl());
                    bean.setId(response.body().getResult().get(i).getId());
                    bean.setName(response.body().getResult().get(i).getName());
                    bean.setTabType(response.body().getResult().get(i).getTabType());
                    bean.setNameType(response.body().getResult().get(i).getNameType());
                    Log.i("getCategory: ",bean.toString());
                    categoryBeans.add(bean);
                }
                setTabLayout();
            }

            @Override
            public void onFailure(Call<CategoryContent<List<CategoryBean>>> call, Throwable t) {

            }

        });


    }

    private void setTabLayout(){
        Log.i("getCategory: ",""+categoryBeans.size());
        for (int i =0; i<categoryBeans.size(); i++){
            Log.i("getCategory: ",categoryBeans.get(i).getName());
            mTabLayout.addTab(mTabLayout.newTab().setText(categoryBeans.get(i).getName()));
        }
    }
}

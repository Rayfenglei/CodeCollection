package com.example.ray.codecollections.view.functionactivity.searchbanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;
import java.util.ArrayList;

public class SearchFragment extends BaseFragment1 implements View.OnClickListener,FilterListener{
    private static final String TAG = "SearchFragment";
    private RecyclerView rvSearch,rvSearchHistory;
    private EditText mEditText;
    private String mEditContent;
    private Button mButton;
    private TextView tvHistory;
    private TextView tvClear;
    private String[] datas;
    private SearchAdapter mAdapter;
    private SearchHistoryAdapter mHistoryAdapter;
    private ArrayList<String> mLists = new ArrayList<>();
    private ArrayList<String> mHistoryLists = new ArrayList<>();
    private SearchDQHelper dqHelper;
    private SQLiteDatabase sqlData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_search);
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void initView() {
        rvSearch = view.findViewById(R.id.rv_search);
        rvSearchHistory = view.findViewById(R.id.rv_search_history);
        mEditText = view.findViewById(R.id.et_search);
        mButton = view.findViewById(R.id.bt_search);
        tvHistory = view.findViewById(R.id.tv_search_history);
        tvClear = view.findViewById(R.id.tv_search_clear);
    }

    @Override
    public void initData() {
        initListData();
        dqHelper = new SearchDQHelper(context.getApplicationContext());
        getHistorySearch();
        // 这里创建adapter的时候，构造方法参数传了一个接口对象，这很关键，回调接口中的方法来实现对过滤后的数据的获取
        mAdapter = new SearchAdapter(context, mLists,this);
        mHistoryAdapter = new SearchHistoryAdapter(context,mHistoryLists);
        rvSearch.setLayoutManager(new LinearLayoutManager(context));
        rvSearch.setItemAnimator(new DefaultItemAnimator());
        rvSearch.setAdapter(mAdapter);
        rvSearchHistory.setLayoutManager(new LinearLayoutManager(context));
        rvSearchHistory.setItemAnimator(new DefaultItemAnimator());
        rvSearchHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void initEvent() {
        mButton.setOnClickListener(this);
        tvHistory.setOnClickListener(this);
        tvClear.setOnClickListener(this);
        mAdapter.setOnClickItemListener(new SearchAdapter.OnClickItemListener() {
            @Override
            public void onClickItem(String s) {
                insertData(s);
            }
        });
        setListeners();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_search:
                insertData(mEditContent);
                break;
            case R.id.tv_search_clear:
                doDeleteHistorySearch();
                break;
        }
    }
    private void setListeners() {
        // 没有进行搜索的时候，也要添加对listView的item单击监听
        rvSearch.setVisibility(View.GONE);
        /**
         * 对编辑框添加文本改变监听，搜索的具体功能在这里实现
         * 很简单，文本该变的时候进行搜索。关键方法是重写的onTextChanged（）方法。
         */
        mEditText.addTextChangedListener(new TextWatcher() {
            /**
             * 编辑框内容改变的时候会执行该方法
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
                if(mAdapter != null){
                    mAdapter.getFilter().filter(s);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (mEditText.getText().length()==0){
                    rvSearch.setVisibility(View.GONE);
                }else {
                    rvSearch.setVisibility(View.VISIBLE);
                    mEditContent = mEditText.getText().toString();
                }
            }
        });
    }
    private void initListData() {
        mLists.add("看着飞舞的尘埃   掉下来");
        mLists.add("没人发现它存在");
        mLists.add("多自由自在");
        mLists.add("可世界都爱热热闹闹");
        mLists.add("容不下   我百无聊赖");
        mLists.add("不应该   一个人 发呆");
        mLists.add("只有我   守着安静的沙漠");
        mLists.add("等待着花开");
        mLists.add("只有我   看着别人的快乐");
    }
    @Override
    public void getFilterData(ArrayList<String> list) {
        //这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
        Log.e("getFilterData", "接口回调成功");
        Log.e("getFilterData", list.toString());
    }
    private void insertData(String s){
        boolean hasData =hasData(s);
        if (!hasData){
            Log.i(TAG," hasData is  "+hasData);
            insertHistorySearch(s);
        }
    }
    /**
     *
     * 插入数据到数据库，即写入搜索字段到历史搜索记录
     */
    private void insertHistorySearch(String str){
        if ( !"".equals(str)){
            Log.i(TAG," insert Data  "+str);
            sqlData = dqHelper.getWritableDatabase();
            sqlData.execSQL("insert into records(name) values('" + str + "')");
            sqlData.close();
            mHistoryAdapter.addDataChanged(str);
        }
    }

    /**
     * 清空数据库
     */
    private void doDeleteHistorySearch(){
        sqlData = dqHelper.getReadableDatabase();
        sqlData.execSQL("delete from records");
        sqlData.close();
        mHistoryAdapter.notifyDataClearChanged();
    }
    /**
     * 检查数据库中是否已经有该搜索记录
     *
     * 直接返回
     */
    private boolean hasData(String str) {
        boolean isExist;
        // 从数据库中Record表里找到name=tempName的id
        Cursor cursor = dqHelper.getReadableDatabase().rawQuery(
                "select id as id,name from records where name =?", new String[]{str});
        //  判断是否有下一个
        isExist = cursor.moveToNext();
        Log.i(TAG,"str " + str+ " hasData "+isExist);
        return isExist;
    }
    /**
     * 查询数据库
     */
    private  void getHistorySearch(){
        Cursor cursor = dqHelper.getReadableDatabase().rawQuery("select name from records",null);
        while (cursor.moveToNext()){
            mHistoryLists.add(cursor.getString(cursor.getColumnIndex("name")));
            Log.i(TAG,"content is  " +cursor.getString(cursor.getColumnIndex("name")));
        }
        cursor.close();
    }
}

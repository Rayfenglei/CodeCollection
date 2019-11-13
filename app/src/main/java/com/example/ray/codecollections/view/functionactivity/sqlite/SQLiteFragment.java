package com.example.ray.codecollections.view.functionactivity.sqlite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ray.codecollections.R;
import com.example.ray.codecollections.base.BaseFragment1;

import java.util.ArrayList;
import java.util.List;

public class SQLiteFragment extends BaseFragment1 implements View.OnClickListener{
    private static final String TAG = "SQLiteFragment";

    private SQLiteMethod ordersMethod;
    private TextView showSQLMsg;
    private EditText inputSqlMsg;
    private ListView showDateListView;
    private List<SQLiteBean> orderList;
    private SQLiteListAdapter adapter;
    private Button executeButton,insertButton,deleteButton,updateButton,query1Button,query2Button,query3Button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_sqlite);
        return view;
    }

    @Override
    public void initView() {
        executeButton = view.findViewById(R.id.executeButton);
        insertButton = view.findViewById(R.id.insertButton);
        deleteButton = view.findViewById(R.id.deleteButton);
        updateButton = view.findViewById(R.id.updateButton);
        query1Button = view.findViewById(R.id.query1Button);
        query2Button = view.findViewById(R.id.query2Button);
        query3Button = view.findViewById(R.id.query3Button);
        inputSqlMsg = view.findViewById(R.id.inputSqlMsg);
        showSQLMsg = view.findViewById(R.id.showSQLMsg);
        showDateListView = view.findViewById(R.id.showDateListView);
    }

    @Override
    public void initData() {
        ordersMethod = new SQLiteMethod(context);
        if (! ordersMethod.isDataExist()){
            ordersMethod.initTable();
        }
        orderList = ordersMethod.getAllDate();
        if (orderList != null){
            adapter = new SQLiteListAdapter(context, orderList);
            showDateListView.setAdapter(adapter);
        }
    }

    @Override
    public void initEvent() {
        executeButton.setOnClickListener(this);
        insertButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        query1Button.setOnClickListener(this);
        query2Button.setOnClickListener(this);
        query3Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.executeButton:
                showSQLMsg.setVisibility(View.GONE);
                //执行在editText中自己输入的SQL命令
                String sql = inputSqlMsg.getText().toString();

                if (! TextUtils.isEmpty(sql)){
                    ordersMethod.execSQL(sql);
                }else
                    Toast.makeText(context, R.string.strInputSql, Toast.LENGTH_SHORT).show();
                //showSQLMsg.setText(sql);
                refreshOrderList();
                break;

            case R.id.insertButton:
                showSQLMsg.setVisibility(View.VISIBLE);
                showSQLMsg.setText("新增一条数据：\n添加数据(7, \"Jne\", 700, \"China\")\ninsert into Orders(Id, CustomName, OrderPrice, Country) values (7, \"Jne\", 700, \"China\")");
                ordersMethod.insertDate();
                refreshOrderList();
                break;

            case R.id.deleteButton:
                showSQLMsg.setVisibility(View.VISIBLE);
                showSQLMsg.setText("删除一条数据：\n删除Id为7的数据\ndelete from Orders where Id = 7");
                ordersMethod.deleteOrder("Arc");
                refreshOrderList();
                break;

            case R.id.updateButton:
                showSQLMsg.setVisibility(View.VISIBLE);
                showSQLMsg.setText("修改一条数据：\n将Id为6的数据的OrderPrice修改了800\nupdate Orders set OrderPrice = 800 where Id = 6");
                ordersMethod.updateOrder();
                refreshOrderList();
                break;

            case R.id.query1Button:
                showSQLMsg.setVisibility(View.VISIBLE);
                StringBuilder msg = new StringBuilder();
                msg.append("数据查询：\n此处将用户名为\"Bor\"的信息提取出来\nselect * from Orders where CustomName = 'Bor'");
                List<SQLiteBean> borOrders = ordersMethod.getBorOrder();
                for (SQLiteBean order : borOrders){
                    msg.append("\n(" + order.id + ", " + order.customName + ", " + order.orderPrice + ", " + order.country + ")");
                }
                showSQLMsg.setText(msg);
                break;

            case R.id.query2Button:
                showSQLMsg.setVisibility(View.VISIBLE);
                int chinaCount = ordersMethod.getChinaCount();
                showSQLMsg.setText("统计查询：\n此处查询Country为China的用户总数\nselect count(Id) from Orders where Country = 'China'\ncount = " + chinaCount);

                showDatas();
                break;

            case R.id.query3Button:
                showSQLMsg.setVisibility(View.VISIBLE);
                StringBuilder msg2 = new StringBuilder();
                msg2.append("比较查询：\n此处查询单笔数据中OrderPrice最高的\nselect Id, CustomName, Max(OrderPrice) as OrderPrice, Country from Orders");
                SQLiteBean order = ordersMethod.getMaxOrderPrice();
                msg2.append("\n(" + order.id + ", " + order.customName + ", " + order.orderPrice + ", " + order.country + ")");
                showSQLMsg.setText(msg2);
                break;
            default:
                break;
        }
    }
    private void refreshOrderList(){
        // 注意：千万不要直接赋值，如：orderList = ordersDao.getAllDate() 此时相当于重新分配了一个内存 原先的内存没改变 所以界面不会有变化
        // Java中的类是地址传递 基本数据才是值传递
        orderList.clear();
        orderList.addAll(ordersMethod.getAllDate());
        adapter.notifyDataSetChanged();
    }
    private void showDatas(){
        ArrayList<SQLiteBean> orders = ordersMethod.getAllDatas();
        if (orders != null && orders.size() > 0) {
            for (SQLiteBean b : orders) {
                Log.i(TAG,  b.getId()+"----"+b.getCustomName()+"----"+b.getOrderPrice()+"----"+b.getCountry());
            }
        }
    }
}

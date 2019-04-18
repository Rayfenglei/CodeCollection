package com.example.ray.codecollections.view.dialogsfragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ray.codecollections.base.BaseFragment;
import com.example.ray.codecollections.R;

public class DialogsFragment extends BaseFragment implements View.OnClickListener{
    private Button btNormal,btList,btSingle,btMulti,btEdit,btDefine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_dialog);

        initView();
        initData();
        initEvent();

        return view;
    }

    @Override
    public void initView() {
        btDefine = view.findViewById(R.id.bt_define_dialog);
        btEdit = view.findViewById(R.id.bt_edit_dialog);
        btNormal = view.findViewById(R.id.bt_normal_dialog);
        btList = view.findViewById(R.id.bt_list_dialog);
        btSingle = view.findViewById(R.id.bt_singlechoice_dialog);
        btMulti = view.findViewById(R.id.bt_multichoice_dialog);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btDefine.setOnClickListener(this);
        btEdit.setOnClickListener(this);
        btNormal.setOnClickListener(this);
        btList.setOnClickListener(this);
        btSingle.setOnClickListener(this);
        btMulti.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_normal_dialog:
                showNormalDialog();
                break;
            case R.id.bt_list_dialog:
                showListDialog();
                break;
            case R.id.bt_singlechoice_dialog:
                showSingleChoiceDialog();
                break;
            case R.id.bt_multichoice_dialog:
                showMultiChoiceDialog();
                break;
            case R.id.bt_edit_dialog:
                showInputDialog();
                break;
            case R.id.bt_define_dialog:
                showDefineDialog();
                break;
        }
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("我是一个普通Dialog");
        normalDialog.setMessage("你要点击哪一个按钮呢?");
        //setPositiveButton按键位置靠右
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        //setNegativeButton按键位置靠左
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void showListDialog() {
        final String[] items = { "我是1","我是2","我是3","我是4" };
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(context);
        listDialog.setTitle("我是一个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                showToast("你点击了" + items[which]);

            }
        });
        listDialog.show();
    }

    int yourChoice;
    private void showSingleChoiceDialog(){
        final String[] items = { "我是1","我是2","我是3","我是4" };
        yourChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(context);
        singleChoiceDialog.setTitle("我是一个单选Dialog");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            showToast("你选择了" + items[yourChoice]);
                        }
                    }
                });
        singleChoiceDialog.show();
    }


    private void showMultiChoiceDialog() {
        final String[] items = { "我是1","我是2","我是3","我是4" };
        // 设置默认选中的选项，全为false默认均未选中
        final boolean initChoiceSets[]={true,false,false,false};

        AlertDialog.Builder multiChoiceDialog =
                new AlertDialog.Builder(context);
        multiChoiceDialog.setTitle("我是一个多选Dialog");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        initChoiceSets[which]=isChecked;

                    }
                });
        multiChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder str = new StringBuilder();//线程不安全，单线程
                        for (int i = 0; i < initChoiceSets.length; i++) {
                            if (initChoiceSets[i]){
                                str.append(items[i]).append(" ");
                            }
                        }
                        showToast("你选中了");

                    }
                });
        multiChoiceDialog.show();
    }

    private void showInputDialog() {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(context);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(context);
        inputDialog.setTitle("我是一个输入Dialog").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(editText.getText().toString());
                    }
                }).show();
    }

    private void showDefineDialog() {
        AlertDialog.Builder defineDialog = new AlertDialog.Builder(context);
        final View defineView = LayoutInflater.from(context).inflate(R.layout.define_dialog,null);
        defineDialog.setTitle("我是一个自定义Dialog").setView(defineView);
        defineDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("自定义Dialog");
                    }
                }).show();

    }

}

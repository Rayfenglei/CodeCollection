//package com.example.ray.codecollections.base;
//
//import android.annotation.TargetApi;
//import android.app.ProgressDialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//
//import com.nbc.browserlib.R;
//import com.nbc.browserlib.interfaces.IBackHandled;
//import com.nbc.browserlib.interfaces.IViewStatus;
//import com.nbc.browserlib.manager.ThreadManager;
//import com.nbc.browserlib.util.CommonUtil;
//import com.nbc.browserlib.util.LogUtil;
//import com.nbc.browserlib.util.StringUtil;
//import com.umeng.analytics.MobclickAgent;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 基础android.support.v4.app.FragmentActivity，通过继承可获取或使用 里面创建的 组件 和 方法
// * *onFling内控制左右滑动手势操作范围，可自定义
// *
// * @use extends BaseActivity, 具体参考 .DemoActivity 和 .DemoFragmentActivity
// * @see #context
// * @see #view
// * @see #fragmentManager
// * @see #setContentView
// * @see #runUiThread
// * @see #runThread
// * @see #onDestroy
// */
//public abstract class BaseActivity<PV, PT extends BasePresenter<PV>> extends FragmentActivity implements IViewConstraint, IViewStatus, IBackHandled {
//    public static final String TAG = "BaseActivity";
//
//    /**
//     * 表示层的引用
//     */
//    public PT mPresenter;
//    /**
//     * 该Activity实例，命名为context是因为大部分方法都只需要context，写成context使用更方便
//     *
//     * @warn 不能在子类中创建
//     */
//    protected BaseActivity context = null;
//    /**
//     * 该Activity的界面，即contentView
//     *
//     * @warn 不能在子类中创建
//     */
//    protected View view = null;
//    /**
//     * 布局解释器
//     *
//     * @warn 不能在子类中创建
//     */
//    protected LayoutInflater inflater = null;
//    /**
//     * Fragment管理器
//     *
//     * @warn 不能在子类中创建
//     */
//    protected FragmentManager fragmentManager = null;
//    protected BaseFragment fragment = null;
//
//    private boolean isAlive = false;
//    private boolean isRunning = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (needNoTitle()) {
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//        }
//
//        mPresenter = createPresenter();
//        if (mPresenter != null) {
//            mPresenter.attachToView((PV) this);
//        }
//
//        context = this;
//        isAlive = true;
//        fragmentManager = getSupportFragmentManager();
//
//        inflater = getLayoutInflater();
//
//        threadNameList = new ArrayList<>();
//
//        BaseBroadcastReceiver.register(context, receiver, ACTION_EXIT_APP);
//    }
//    protected boolean needNoTitle() {
//        return true;
//    }
//
//    @Override
//    public void setSelectedFragment(BaseFragment fragment) {
//        this.fragment = fragment;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (fragment != null && fragment.onBackPressed()) {
//            return;
//        }
//        super.onBackPressed();
//    }
//
//    protected abstract PT createPresenter();
//
//    /**
//     * 默认标题TextView，layout.xml中用@id/tvBaseTitle绑定。子Activity内调用autoSetTitle方法 会优先使用INTENT_TITLE
//     *
//     * @warn 如果子Activity的layout中没有android:id="@id/tvBaseTitle"的TextView，使用前必须在子Activity中赋值
//     * @see #
//     */
//    @Nullable
//    //protected TextView tvBaseTitle;
//
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    @Override
//    public void setContentView(int layoutResID) {
//        super.setContentView(layoutResID);
//
//        // 状态栏沉浸，4.4+生效 <<<<<<<<<<<<<<<<<
//        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.color_33cc99);//状态背景色，可传drawable资源*/
//        // 状态栏沉浸，4.4+生效 >>>>>>>>>>>>>>>>>
//
//        //tvBaseTitle = findView(R.id.tvBaseTitle);//绑定默认标题TextView
//    }
//
//    //底部滑动实现同点击标题栏左右按钮效果<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//    /**
//     * 用于 打开activity以及activity之间的通讯（传值）等；一些通讯相关基本操作（打电话、发短信等）
//     */
//    protected Intent intent = null;
//
//    /**
//     * 退出时之前的界面进入动画,可在finish();前通过改变它的值来改变动画效果
//     */
//    protected int enterAnim = R.anim.null_anim;
//    /**
//     * 退出时该界面动画,可在finish();前通过改变它的值来改变动画效果
//     */
//    protected int exitAnim = R.anim.null_anim;
//
//    /**
//     * 通过id查找并获取控件，使用时不需要强转
//     *
//     * @param id
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    /*public <V extends View> V findView(int id) {
//        return (V) findViewById(id);
//    }*/
//
//    //自动设置标题方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//    //自动设置标题方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//    //显示与关闭进度弹窗方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//    /**
//     * 进度弹窗
//     */
//    protected ProgressDialog progressDialog = null;
//
//    /**
//     * 展示加载进度条,无标题
//     *
//     * @param stringResId
//     */
//    public void showProgressDialog(int stringResId) {
//        try {
//            showProgressDialog(null, context.getResources().getString(stringResId));
//        } catch (Exception e) {
//            LogUtil.e(TAG, "showProgressDialog  showProgressDialog(null, context.getResources().getString(stringResId));");
//        }
//    }
//
//    /**
//     * 展示加载进度条,无标题
//     *
//     * @param message
//     */
//    public void showProgressDialog(String message) {
//        showProgressDialog(null, message);
//    }
//
//    /**
//     * 展示加载进度条
//     *
//     * @param title   标题
//     * @param message 信息
//     */
//    public void showProgressDialog(final String title, final String message) {
//        runUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (progressDialog == null) {
//                    progressDialog = new ProgressDialog(context);
//                }
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                if (StringUtil.isNotEmpty(title, false)) {
//                    progressDialog.setTitle(title);
//                }
//                if (StringUtil.isNotEmpty(message, false)) {
//                    progressDialog.setMessage(message);
//                }
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.show();
//            }
//        });
//    }
//
//
//    /**
//     * 隐藏加载进度
//     */
//    public void dismissProgressDialog() {
//        runUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //把判断写在runOnUiThread外面导致有时dismiss无效，可能不同线程判断progressDialog.isShowing()结果不一致
//                if (progressDialog == null || !progressDialog.isShowing()) {
//                    LogUtil.w(TAG, "dismissProgressDialog  progressDialog == null" +
//                            " || progressDialog.isShowing() == false >> return;");
//                    return;
//                }
//                progressDialog.dismiss();
//            }
//        });
//    }
//    //显示与关闭进度弹窗方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//    //启动新Activity方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//    /**
//     * 打开新的Activity，向左滑入效果
//     *
//     * @param intent
//     */
//    public void toActivity(Intent intent) {
//        toActivity(intent, true);
//    }
//
//    /**
//     * 打开新的Activity
//     *
//     * @param intent
//     * @param showAnimation
//     */
//    public void toActivity(Intent intent, boolean showAnimation) {
//        toActivity(intent, -1, showAnimation);
//    }
//
//    /**
//     * 打开新的Activity，向左滑入效果
//     *
//     * @param intent
//     * @param requestCode
//     */
//    public void toActivity(Intent intent, int requestCode) {
//        toActivity(intent, requestCode, true);
//    }
//
//    /**
//     * 打开新的Activity
//     *
//     * @param intent
//     * @param requestCode
//     * @param showAnimation
//     */
//    public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
//        runUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (intent == null) {
//                    LogUtil.w(TAG, "toActivity  intent == null >> return;");
//                    return;
//                }
//                //fragment中使用context.startActivity会导致在fragment中不能正常接收onActivityResult
//                if (requestCode < 0) {
//                    startActivity(intent);
//                } else {
//                    startActivityForResult(intent, requestCode);
//                }
//                if (showAnimation) {
//                    overridePendingTransition(R.anim.right_push_in, R.anim.hold);
//                } else {
//                    overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
//                }
//            }
//        });
//    }
//    //启动新Activity方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//
//    //show short toast 方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//    /**
//     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
//     *
//     * @param stringResId
//     */
//    public void showShortToast(int stringResId) {
//        try {
//            showShortToast(context.getResources().getString(stringResId));
//        } catch (Exception e) {
//            LogUtil.e(TAG, "showShortToast  context.getResources().getString(resId)" +
//                    " >>  catch (Exception e) {" + e.getMessage());
//        }
//    }
//
//    /**
//     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
//     *
//     * @param string
//     */
//    public void showShortToast(String string) {
//        showShortToast(string, false);
//    }
//
//    /**
//     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
//     *
//     * @param string
//     * @param isForceDismissProgressDialog
//     */
//    public void showShortToast(final String string, final boolean isForceDismissProgressDialog) {
//        runUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (isForceDismissProgressDialog) {
//                    dismissProgressDialog();
//                }
//                CommonUtil.showShortToast(context, "" + string);
//            }
//        });
//    }
//    //show short toast 方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//
//    //运行线程 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//    /**
//     * 在UI线程中运行，建议用这个方法代替runOnUiThread
//     *
//     * @param action
//     */
//    public final void runUiThread(Runnable action) {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "runUiThread  isAlive() == false >> return;");
//            return;
//        }
//        runOnUiThread(action);
//    }
//
//    /**
//     * 线程名列表
//     */
//    protected List<String> threadNameList;
//
//    /**
//     * 运行线程
//     *
//     * @param name
//     * @param runnable
//     * @return
//     */
//    public final Handler runThread(String name, Runnable runnable) {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "runThread  isAlive() == false >> return null;");
//            return null;
//        }
//        name = StringUtil.getTrimedString(name);
//        Handler handler = ThreadManager.getInstance().runThread(name, runnable);
//        if (handler == null) {
//            LogUtil.e(TAG, "runThread handler == null >> return null;");
//            return null;
//        }
//
//        if (!threadNameList.contains(name)) {
//            threadNameList.add(name);
//        }
//        return handler;
//    }
//
//    //运行线程 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//    /**
//     * 一般用于对不支持的数据的处理，比如onCreate中获取到不能接受的id(id<=0)可以这样处理
//     */
//    public void finishWithError(String error) {
//        showShortToast(error);
//        enterAnim = exitAnim = R.anim.null_anim;
//        finish();
//    }
//
//    @Override
//    public void finish() {
//        super.finish();//必须写在最前才能显示自定义动画
//        runUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (enterAnim > 0 && exitAnim > 0) {
//                    try {
//                        overridePendingTransition(enterAnim, exitAnim);
//                    } catch (Exception e) {
//                        LogUtil.e(TAG, "finish overridePendingTransition(enterAnim, exitAnim);" +
//                                " >> catch (Exception e) {  " + e.getMessage());
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        LogUtil.d(TAG, "\n onResume <<<<<<<<<<<<<<<<<<<<<<<");
//        super.onResume();
//        isRunning = true;
//        LogUtil.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>\n");
//        MobclickAgent.onResume(this); // 基础指标统计，不能遗漏
//    }
//
//    @Override
//    protected void onPause() {
//        LogUtil.d(TAG, "\n onPause <<<<<<<<<<<<<<<<<<<<<<<");
//        super.onPause();
//        isRunning = false;
//        LogUtil.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>\n");
//        MobclickAgent.onPause(this); // 基础指标统计，不能遗漏
//    }
//
//    /**
//     * 销毁并回收内存
//     *
//     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
//     */
//    @Override
//    protected void onDestroy() {
//        LogUtil.d(TAG, "\n onDestroy <<<<<<<<<<<<<<<<<<<<<<<");
//        if (mPresenter != null) {
//            mPresenter.deAttachFromView();
//        }
//        dismissProgressDialog();
//        BaseBroadcastReceiver.unregister(context, receiver);
//        ThreadManager.getInstance().destroyThread(threadNameList);
//        if (view != null) {
//            try {
//                view.destroyDrawingCache();
//            } catch (Exception e) {
//                LogUtil.w(TAG, "onDestroy  try { view.destroyDrawingCache();" +
//                        " >> } catch (Exception e) {\n" + e.getMessage());
//            }
//        }
//
//        isAlive = false;
//        isRunning = false;
//        super.onDestroy();
//
//        inflater = null;
//        view = null;
//
//        fragmentManager = null;
//        progressDialog = null;
//        threadNameList = null;
//
//        intent = null;
//
//        context = null;
//
//        LogUtil.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>\n");
//    }
//
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//
//        public void onReceive(Context context, Intent intent) {
//            String action = intent == null ? null : intent.getAction();
//            if (!isAlive() || StringUtil.isNotEmpty(action, true)) {
//                LogUtil.e(TAG, "receiver.onReceive  isAlive() == false" +
//                        " || StringUtil.isNotEmpty(action, true) == false >> return;");
//                return;
//            }
//
//            if (ACTION_EXIT_APP.equals(action)) {
//                finish();
//            }
//        }
//    };
//
//    @Override
//    public final boolean isAlive() {
//        return isAlive && context != null;// & ! isFinishing();导致finish，onDestroy内runUiThread不可用
//    }
//
//    @Override
//    public final boolean isRunning() {
//        return isRunning & isAlive();
//    }
//
//    /**
//     * 设置状态栏图标颜色
//     * @param isLight true - 浅色图标; false - 深色图标
//     */
//    public void setStatusBarColor(boolean isLight) {
//        View decorView = getWindow().getDecorView();
//        int values = !isLight ? (decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) :
//                (decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        decorView.setSystemUiVisibility(values);
//    }
//
//    /**
//     * 设置状态栏透明
//     */
//    public void setTranslucentStatus() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            View decorView = getWindow().getDecorView();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//    }
//}
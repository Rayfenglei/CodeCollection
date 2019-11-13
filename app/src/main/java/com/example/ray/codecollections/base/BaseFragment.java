//package com.example.ray.codecollections.base;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.nbc.browserlib.R;
//import com.nbc.browserlib.interfaces.IBackHandled;
//import com.nbc.browserlib.interfaces.IViewStatus;
//import com.nbc.browserlib.util.LogUtil;
//
///**
// * 基础android.support.v4.app.Fragment，通过继承可获取或使用 里面创建的 组件 和 方法
// *
// * @use extends BaseFragment, 具体参考.DemoFragment
// * @see #context
// * @see #view
// * @see #onCreateView
// * @see #setContentView
// * @see #runUiThread
// * @see #runThread
// * @see #onDestroy
// */
//public abstract class BaseFragment<PV, PT extends BasePresenter<PV>> extends Fragment implements IViewConstraint, IViewStatus {
//    private static final String TAG = "BaseFragment";
//
//    /**
//     * 表示层的引用
//     */
//    public PT mPresenter;
//    /**
//     * 添加该Fragment的Activity
//     *
//     * @warn 不能在子类中创建
//     */
//    protected BaseActivity context = null;
//    /**
//     * 该Fragment全局视图
//     *
//     * @must 非abstract子类的onCreateView中return view;
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
//     * 添加这个Fragment视图的布局
//     *
//     * @warn 不能在子类中创建
//     */
//    @Nullable
//    protected ViewGroup container = null;
//
//    private boolean isAlive = false;
//    protected boolean isRunning = false;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    /**
//     * @must 在非abstract子类的onCreateView中super.onCreateView且return view;
//     */
//    @Override
//    @Nullable
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (getActivity() instanceof IBackHandled) {
//            this.backHandled = (IBackHandled) getActivity();
//        } else {
//            throw new ClassCastException("Activity must implement BackHandledInterface");
//        }
//        context = (BaseActivity) getActivity();
//        isAlive = true;
//        this.inflater = inflater;
//        this.container = container;
//
//        mPresenter = createPresenter();
//        if (mPresenter != null) {
//            mPresenter.attachToView((PV) this);
//        }
//        return view;
//    }
//
//    protected IBackHandled backHandled;
//
//    protected abstract PT createPresenter();
//
//    public abstract boolean onBackPressed();
//
//    /**
//     * 设置界面布局
//     *
//     * @param layoutResID
//     * @warn 最多调用一次
//     * @use 在onCreateView后调用
//     */
//    public void setContentView(int layoutResID) {
//        setContentView(inflater.inflate(layoutResID, container, false));
//    }
//
//    /**
//     * 设置界面布局
//     *
//     * @param v
//     * @warn 最多调用一次
//     * @use 在onCreateView后调用
//     */
//    public void setContentView(View v) {
//        setContentView(v, null);
//    }
//
//    /**
//     * 设置界面布局
//     *
//     * @param v
//     * @param params
//     * @warn 最多调用一次
//     * @use 在onCreateView后调用
//     */
//    public void setContentView(View v, ViewGroup.LayoutParams params) {
//        view = v;
//    }
//
//
//    /**
//     * 可用于 打开activity与fragment，fragment与fragment之间的通讯（传值）等
//     */
//    protected Bundle argument = null;
//    /**
//     * 可用于 打开activity以及activity之间的通讯（传值）等；一些通讯相关基本操作（打电话、发短信等）
//     */
//    protected Intent intent = null;
//
//    public Intent getIntent() {
//        return context.getIntent();
//    }
//
//    //运行线程<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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
//        if (context == null) {
//            LogUtil.w(TAG, "runUiThread  context == null >> return;");
//            return;
//        }
//        context.runUiThread(action);
//    }
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
//        return context.runThread(name + hashCode(), runnable);//name, runnable);同一Activity出现多个同名Fragment可能会出错
//    }
//
//    //运行线程>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//
//    //进度弹窗<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//    /**
//     * 展示加载进度条,无标题
//     *
//     * @param stringResId
//     */
//    public void showProgressDialog(int stringResId) {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
//            return;
//        }
//        context.showProgressDialog(context.getResources().getString(stringResId));
//    }
//
//    /**
//     * 展示加载进度条,无标题
//     *
//     * @param dialogMessage
//     */
//    public void showProgressDialog(String dialogMessage) {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
//            return;
//        }
//        context.showProgressDialog(dialogMessage);
//    }
//
//    /**
//     * 展示加载进度条
//     *
//     * @param dialogTitle   标题
//     * @param dialogMessage 信息
//     */
//    public void showProgressDialog(String dialogTitle, String dialogMessage) {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
//            return;
//        }
//        context.showProgressDialog(dialogTitle, dialogMessage);
//    }
//
//    /**
//     * 隐藏加载进度
//     */
//    public void dismissProgressDialog() {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "dismissProgressDialog  isAlive() == false >> return;");
//            return;
//        }
//        context.dismissProgressDialog();
//    }
//    //进度弹窗>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//    //启动Activity<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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
//                    context.overridePendingTransition(R.anim.right_push_in, R.anim.hold);
//                } else {
//                    context.overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
//                }
//            }
//        });
//    }
//    //启动Activity>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//
//    //show short toast<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//
//    /**
//     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
//     *
//     * @param stringResId
//     */
//    public void showShortToast(int stringResId) {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
//            return;
//        }
//        context.showShortToast(stringResId);
//    }
//
//    /**
//     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
//     *
//     * @param string
//     */
//    public void showShortToast(String string) {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
//            return;
//        }
//        context.showShortToast(string);
//    }
//
//    /**
//     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
//     *
//     * @param string
//     * @param isForceDismissProgressDialog
//     */
//    public void showShortToast(String string, boolean isForceDismissProgressDialog) {
//        if (!isAlive()) {
//            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
//            return;
//        }
//        context.showShortToast(string, isForceDismissProgressDialog);
//    }
//    //show short toast>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//
//    @Override
//    public final boolean isAlive() {
//        return isAlive && context != null;// & ! isRemoving();导致finish，onDestroy内runUiThread不可用
//    }
//
//    @Override
//    public final boolean isRunning() {
//        return isRunning & isAlive();
//    }
//
//    @Override
//    public void onResume() {
//        LogUtil.d(TAG, "\n onResume <<<<<<<<<<<<<<<<<<<<<<<");
//        super.onResume();
//        isRunning = true;
//        LogUtil.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>\n");
//    }
//
//    @Override
//    public void onPause() {
//        LogUtil.d(TAG, "\n onPause <<<<<<<<<<<<<<<<<<<<<<<");
//        super.onPause();
//        isRunning = false;
//        LogUtil.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>\n");
//    }
//
//    /**
//     * 销毁并回收内存
//     *
//     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
//     */
//    @Override
//    public void onDestroy() {
//        LogUtil.d(TAG, "\n onDestroy <<<<<<<<<<<<<<<<<<<<<<<");
//        if (null != mPresenter) {
//            mPresenter.deAttachFromView();
//        }
//        dismissProgressDialog();
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
//        view = null;
//        inflater = null;
//        container = null;
//
//        intent = null;
//        argument = null;
//
//        context = null;
//
//        LogUtil.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>\n");
//    }
//
//    public void onShow() {
//
//    }
//
//    public void onHide() {
//
//    }
//
//    public void setStatusBarColor(boolean isLight) {
//        View decorView = getActivity().getWindow().getDecorView();
//        int values = !isLight ? (decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) :
//                (decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        decorView.setSystemUiVisibility(values);
//
//    }
//}
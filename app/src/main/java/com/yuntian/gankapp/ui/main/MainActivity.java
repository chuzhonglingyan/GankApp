package com.yuntian.gankapp.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yuntian.basecomponent.databinding.BaseDataBindingActivity;
import com.yuntian.baselibs.util.FragmentHelper;
import com.yuntian.gankapp.R;
import com.yuntian.gankapp.databinding.ActivityMainBinding;
import com.yuntian.gankapp.storage.cons.AppConstants;
import com.yuntian.gankapp.ui.gank.GankMainFragment;
import com.yuntian.gankapp.ui.music.PhotoMainFragment;

import java.util.List;
import java.util.Stack;

/**
 * description show/hide切换fragment.
 * Created by ChuYingYan on 2018/5/7.
 */
public class MainActivity extends BaseDataBindingActivity<ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener {

    private int mItemId = R.id.nav_camera;
    private Stack<Integer> stackTabs = new Stack<>();
    private List<Fragment> fragmentList = new Stack<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initDrawerLayout(mViewBinding.drawerLayout, mViewBinding.navView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mItemId = savedInstanceState.getInt(AppConstants.HOME_CURRENT_TAB_POSITION);
        }
        switchTo(mItemId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(AppConstants.HOME_CURRENT_TAB_POSITION, mItemId);
        LogUtils.d("HOME_CURRENT_TAB_POSITION=" + mItemId);
    }


    /**
     * 初始化 DrawerLayout
     *
     * @param drawerLayout DrawerLayout
     * @param navView      NavigationView
     */
    private void initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                switchTo(mItemId);
            }
        });
        navView.setNavigationItemSelectedListener(this);
    }


    public void addToStack(int id) {
        if (!stackTabs.contains(id)) {
            stackTabs.push(id);
        }

    }


    /**
     * 切换
     */
    private void switchTo(int position) {
        switch (position) {
            //新闻
            case R.id.nav_camera:
                stackTabs.clear();
                FragmentHelper.addHideShowFragment(this, fragmentList, GankMainFragment.class, R.id.fl_container, GankMainFragment.TAG);
                addToStack(R.id.nav_camera);
                break;
            //图片
            case R.id.nav_gallery:
                FragmentHelper.addHideShowFragment(this, fragmentList, PhotoMainFragment.class, R.id.fl_container, PhotoMainFragment.TAG);
                addToStack(R.id.nav_gallery);
                break;
            //视频
            case R.id.nav_slideshow:
                ToastUtils.showShort("视频");
                break;
            //设置
            case R.id.nav_manage:
                ToastUtils.showShort("设置");
                break;
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        // 获取堆栈里有几个
        final int stackEntryCount = stackTabs.size();
        if (mViewBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (stackEntryCount <= 1) {
            exit(); // 如果剩一个说明在主页，提示按两次退出app
        } else {
            final int tabId = stackTabs.get(stackEntryCount - 2);
            mViewBinding.navView.setCheckedItem(tabId);
            stackTabs.pop();
            switchTo(tabId);
            mItemId = tabId;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mViewBinding.drawerLayout.closeDrawer(GravityCompat.START);
        mItemId = item.getItemId();
        if (item.isChecked()) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mViewBinding.drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private long mExitTime = 0;

    /**
     * 退出
     */
    private void exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtils.showShort("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentList.clear();
        stackTabs.clear();
    }


}

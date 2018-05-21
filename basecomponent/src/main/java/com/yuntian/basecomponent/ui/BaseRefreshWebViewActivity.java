package com.yuntian.basecomponent.ui;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yuntian.baselibs.R;
import com.yuntian.baselibs.base.BaseWebViewActivity;


/**
 * link @(https://juejin.im/post/59a56b2151882524424a1862)
 * https://www.jianshu.com/p/a800e66c8949
 * description WebView基本用法 .
 * Created by ChuYingYan on 2018/5/19.
 */
public abstract class BaseRefreshWebViewActivity extends BaseWebViewActivity implements OnRefreshListener {


    protected SmartRefreshLayout smartRefreshLayout;
    protected Toolbar toolBar;


    protected void init() {
        rooView = new LinearLayout(this);
        rooView.setOrientation(LinearLayout.VERTICAL);
        rooView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        initToolBar();
        initSmartRefreshLayout();
        rooView.addView(toolBar);
        rooView.addView(smartRefreshLayout);
    }


    public void  initToolBar(){
        toolBar = new Toolbar(this);
        toolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(50)));
        toolBar.setBackgroundColor(getResources().getColor(R.color.blue_check));
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setMinimumHeight(SizeUtils.dp2px(50));
        toolBar.setNavigationIcon(R.mipmap.icon_back);
        toolBar.setTitleTextAppearance(this, R.style.Toolbar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void  initSmartRefreshLayout(){
        smartRefreshLayout = new SmartRefreshLayout(this);
        smartRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        smartRefreshLayout.setPrimaryColorsId(android.R.color.holo_blue_light, android.R.color.white);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        smartRefreshLayout.setEnableLoadMore(false);

        mWebView = new WebView(this);
        smartRefreshLayout.addView(mWebView);
        smartRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }


}

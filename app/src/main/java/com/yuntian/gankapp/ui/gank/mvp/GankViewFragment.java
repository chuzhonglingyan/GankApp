package com.yuntian.gankapp.ui.gank.mvp;

import android.databinding.ViewDataBinding;

import com.yuntian.basecomponent.mvp.BaseMvpFrgament;
import com.yuntian.basecomponent.mvp.BasePresenter;
import com.yuntian.gankapp.entity.GankInfo;

import java.util.List;

/**
 * description 适配类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public  abstract class GankViewFragment<B extends ViewDataBinding,P extends BasePresenter> extends BaseMvpFrgament<P, B> implements GankContract.View {


    @Override
    public void getWelfarePhotos(List<GankInfo> result) {

    }

    @Override
    public void showMsg(String message, int code) {

    }



}

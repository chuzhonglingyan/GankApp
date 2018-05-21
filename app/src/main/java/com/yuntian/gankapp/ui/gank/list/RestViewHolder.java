package com.yuntian.gankapp.ui.gank.list;

import android.view.View;

import com.yuntian.basecomponent.databinding.BaseBindingViewHolder;
import com.yuntian.gankapp.databinding.ItemGankRestListBinding;
import com.yuntian.gankapp.entity.GankInfo;


/**
 * description  ItemNewsListBinding.
 * Created by ChuYingYan on 2018/5/4.
 */
public class RestViewHolder extends BaseBindingViewHolder<ItemGankRestListBinding, GankInfo> {


    public RestViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewData(GankInfo info, int pos) {
        mBinding.setGanRestItem(info);
    }


}

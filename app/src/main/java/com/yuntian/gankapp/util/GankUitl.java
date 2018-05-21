package com.yuntian.gankapp.util;


import android.text.TextUtils;

import static com.yuntian.gankapp.storage.cons.AppConstants.GANK_REST;
import static com.yuntian.gankapp.storage.cons.AppConstants.GANK_WELFARE;


/**
 * description  .
 * Created by ChuYingYan on 2018/5/8.
 */
public class GankUitl {


    /**
     * all |福利 | 休息视频 |
     *
     * @param datyType
     */
    public static int getDataType(String datyType) {
        if (TextUtils.equals(datyType, GANK_WELFARE)) {
            return ViewHolderUtil.ITEM_TYPE_GANK_WELFARE;
        } else if (TextUtils.equals(datyType, GANK_REST)) {
            return ViewHolderUtil.ITEM_TYPE_GANK_REST;
        } else {
            return ViewHolderUtil.ITEM_TYPE_GANK_ARTICLE;
        }
    }


    /**
     * 计算图片要显示的高度
     *
     * @param pixel 原始分辨率
     * @param width 要显示的宽度
     * @return
     */
    public static int calcPhotoHeight(String pixel, int width) {
        int height = 100;
        try {
            int index = pixel.indexOf("*");
            if (index != -1) {

                int widthPixel = Integer.parseInt(pixel.substring(0, index));
                int heightPixel = Integer.parseInt(pixel.substring(index + 1));
                height = (int) (heightPixel * (width * 1.0f / widthPixel));
            }
        } catch (NumberFormatException e) {
        }
        return height;
    }


    /**
     * https://developer.qiniu.com/dora/manual/1279/basic-processing-images-imageview2*
     *
     * @param url:http://img.gank.io/6ade6383-bc8e-40e4-9919-605901ad0ca5?imageView2/0/w/100/h/100
     * @param width
     * @param height
     */
    public static String getRequireImageUrl(String url, int width, int height) {
        if (!TextUtils.isEmpty(url) && url.contains("img.gank.io")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(url).append("?imageView2/0")
                    .append("/w/").append(width).append("/h/").append(height);
            return stringBuilder.toString();
        }
        return url;
    }

}

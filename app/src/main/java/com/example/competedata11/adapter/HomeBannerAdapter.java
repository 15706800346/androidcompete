package com.example.competedata11.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.competedata11.bean.HomeBannerBean;
import com.example.competedata11.utils.Constants;
import com.youth.banner.loader.ImageLoader;

public class HomeBannerAdapter extends ImageLoader {
    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        HomeBannerBean.RowsBean rowsBean=(HomeBannerBean.RowsBean)o;
        Glide.with(context)
                .load(Constants.WEB_URL+rowsBean.getAdvImg())
                .into(imageView);
    }
}

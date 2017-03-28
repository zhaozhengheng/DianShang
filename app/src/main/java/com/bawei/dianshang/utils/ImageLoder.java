package com.bawei.dianshang.utils;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by 1 on 2017/3/17.
 */
public class ImageLoder extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        ImageLoaderConfiguration im=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(im);
        SDKInitializer.initialize(getApplicationContext());

    }
}

package com.bwei.wangjianxun20171123.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者：王建勋
 * 时间：2017-11-23 15:40
 * 类的用途：Universal-Image-Loader图片加载框架
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}

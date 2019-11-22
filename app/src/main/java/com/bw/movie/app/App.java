package com.bw.movie.app;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

public class App extends Application {
    //全局上下文
    private static App sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Fresco.initialize(this, ImagePipelineConfig.newBuilder(this)
                //图片缓存路径
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this)
                        .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory().getAbsolutePath()))
                        .setMaxCacheSize(10*1024*1024)
                        .build())
                .build());
    }

    public static App getAppContext() {
        return sContext;
    }
}

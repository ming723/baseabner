package com.abner.ming.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.abner.ming.base.utils.CacheUtils;
import com.facebook.drawee.backends.pipeline.Fresco;


public class AbnerApplication extends MultiDexApplication {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
        Fresco.initialize(this);
        CacheUtils.getCacheUtils().init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static Context getContext() {
        return app;
    }
}

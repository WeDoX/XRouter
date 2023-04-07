package com.onedream.xrouter.base;

import android.app.Application;

import com.onedream.xrouter_api.XRouter;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //
        XRouter.init(this);
    }
}

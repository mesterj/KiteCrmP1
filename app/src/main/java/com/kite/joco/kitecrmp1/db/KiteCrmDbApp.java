package com.kite.joco.kitecrmp1.db;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Joco on 2015.05.24..
 */

public class KiteCrmDbApp extends Application {

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}

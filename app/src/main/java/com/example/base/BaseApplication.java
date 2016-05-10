package com.example.base;

import android.app.Application;
import android.content.Context;

import com.example.base.config.AppComponent;
import com.example.base.config.ApplicationModule;
import com.example.base.config.DaggerAppComponent;

/**
 * Created by Wagos.
 */
public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        return ((BaseApplication) context.getApplicationContext()).appComponent;
    }
}

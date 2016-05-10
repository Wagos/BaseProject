package com.example.base.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.base.BaseApplication;
import com.example.base.config.AppComponent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by Wagos.
 */
abstract public class BaseActivity extends AppCompatActivity {

    protected EventBus eventBus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        setUpComponent(BaseApplication.getAppComponent(this));
    }

    protected abstract int getContentView();

    protected void setUpComponent(AppComponent appComponent) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
    }
}

package com.example.base.ui;

import android.content.Context;
import android.content.Intent;

import com.example.base.R;
import com.example.base.config.AppComponent;
import com.example.base.event.ErrorEvent;
import com.example.base.service.BaseService;
import com.example.base.service.Repository;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    @Inject
    protected BaseService service;

    @Inject
    protected Repository repository;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Subscribe
    public void onError(ErrorEvent event){

    }
}

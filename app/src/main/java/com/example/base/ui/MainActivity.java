package com.example.base.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.base.R;
import com.example.base.config.AppComponent;
import com.example.base.config.SettingsPrefs;
import com.example.base.service.BaseService;
import com.example.base.service.Repository;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingsPrefs settingsPrefs = repository.getPreferences();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}

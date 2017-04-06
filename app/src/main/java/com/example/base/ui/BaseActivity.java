package com.example.base.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.base.BaseApplication;
import com.example.base.config.AppComponent;
import com.example.base.event.ErrorEvent;
import com.example.base.util.ValidationUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ValidationUtils.PERMISSION_REQUEST_CODE) {
            ArrayList<String> deniedPermissions = ValidationUtils.getNeverAskPermissions(this, permissions, grantResults);
            if (deniedPermissions.isEmpty()) {
                allPermissionsGranted();
            } else {
                showPermissionRequired();
            }
        }
    }

    protected void showPermissionRequired() {

    }

    protected void allPermissionsGranted() {

    }

    @Subscribe
    public void onError(ErrorEvent event) {

    }
}

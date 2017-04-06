package com.example.base.ui;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.base.event.ErrorEvent;
import com.example.base.util.ValidationUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Wagos.
 */

public abstract class BaseFragment extends Fragment {
    private final EventBus BUS = EventBus.getDefault();
    private Unbinder unbinder;

    protected abstract int getContentView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!BUS.isRegistered(this)) {
            BUS.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BUS.isRegistered(this)) {
            BUS.unregister(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean granted = ValidationUtils.checkPermissionsGranted(grantResults);
        if (granted) {
            allPermissionGranted(requestCode);
        } else {
            showPermissionRequired();
        }
    }

    protected void showPermissionRequired() {
    }

    @Subscribe
    public void onError(ErrorEvent event) {

    }

    protected void allPermissionGranted(int requestCode) {

    }

    protected int getColor(@ColorRes int res) {
        return ContextCompat.getColor(getContext(), res);
    }

    protected void showToast(@StringRes final int msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}

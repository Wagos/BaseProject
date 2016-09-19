package com.example.base.service;

import com.example.base.event.ErrorEvent;
import com.example.base.event.RequestType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Wagos.
 */
public class BaseCallback <T> implements Callback<T> {

    private final RequestType requestType;

    public BaseCallback(RequestType requestType) {
        this.requestType = requestType;
    }

    protected void onSuccess(T body) {
        EventBus.getDefault().post(body);
    }

    private void postError() {
        EventBus.getDefault().post(new ErrorEvent(requestType));
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()) {
            final T body = response.body();
            onSuccess(body);
        }else {
            postError();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(!call.isCanceled()) {
            postError();
        }
    }
}

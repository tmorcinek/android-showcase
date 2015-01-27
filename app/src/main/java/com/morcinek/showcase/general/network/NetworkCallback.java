package com.morcinek.showcase.general.network;

import android.os.Handler;

import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.general.network.response.ProgressController;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
class NetworkCallback<T> implements Callback<T> {

    private final Handler handler = new Handler();

    private NetworkResponseListener<T> responseListener;

    private ProgressController[] progressControllers;

    public NetworkCallback(NetworkResponseListener<T> responseListener, ProgressController... progressControllers) {
        this.responseListener = responseListener;
        this.progressControllers = progressControllers;
        preExecute();
    }

    private void preExecute() {
        if (progressControllers != null) {
            for (ProgressController progressController : progressControllers) {
                progressController.preExecute();
            }
        }
    }

    private void postExecuteWithSuccess(boolean success) {
        if (progressControllers != null) {
            for (ProgressController progressController : progressControllers) {
                progressController.postExecuteWithSuccess(success);
            }
        }
    }

    @Override
    public void success(final T object, Response response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                responseListener.success(object);
                postExecuteWithSuccess(true);
            }
        });
    }

    @Override
    public void failure(final RetrofitError error) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                responseListener.failure(error);
                postExecuteWithSuccess(false);
            }
        });
    }
}

package com.morcinek.showcase.general.network;

import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.general.network.response.ProgressController;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class NetworkCallback<T> implements Callback<T> {

    private NetworkResponseListener<T> responseListener;

    private ProgressController[] progressControllers;

    public NetworkCallback(NetworkResponseListener<T> responseListener, ProgressController... progressControllers) {
        this.responseListener = responseListener;
        this.progressControllers = progressControllers;
        preExecute();
    }

    public void cancel() {
        responseListener = null;
        postExecuteWithSuccess(false);
        progressControllers = new ProgressController[0];
    }

    private void preExecute() {
        for (ProgressController progressController : progressControllers) {
            progressController.preExecute();
        }
    }

    private void postExecuteWithSuccess(boolean success) {
        for (ProgressController progressController : progressControllers) {
            progressController.postExecuteWithSuccess(success);
        }
    }

    @Override
    public void success(final T object, Response response) {
        if (responseListener != null) {
            responseListener.success(object);
            postExecuteWithSuccess(true);
        }
    }

    @Override
    public void failure(final RetrofitError error) {
        if (responseListener != null) {
            responseListener.failure(error);
            postExecuteWithSuccess(false);
        }
    }
}

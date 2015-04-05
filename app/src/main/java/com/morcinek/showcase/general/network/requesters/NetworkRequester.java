package com.morcinek.showcase.general.network.requesters;

import com.morcinek.showcase.general.network.NetworkCallback;
import com.morcinek.showcase.general.network.api.ApiService;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.general.network.response.ProgressController;

import retrofit.Callback;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public abstract class NetworkRequester<T> {

    protected final ApiService apiService;

    private NetworkResponseListener<T> responseListener;
    private ProgressController[] progressControllers;

    private NetworkCallback<T> networkCallback;

    public NetworkRequester(ApiService apiService) {
        this.apiService = apiService;
    }

    public void initialize(NetworkResponseListener<T> responseListener, ProgressController... progressControllers) {
        this.responseListener = responseListener;
        this.progressControllers = progressControllers;
    }

    protected Callback<T> provideCallback() {
        networkCallback = new NetworkCallback<>(responseListener, progressControllers);
        return networkCallback;
    }

    public void cancelRequest() {
        networkCallback.cancel();
    }
}

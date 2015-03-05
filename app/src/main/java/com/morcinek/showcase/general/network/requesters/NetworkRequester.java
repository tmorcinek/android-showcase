package com.morcinek.showcase.general.network.requesters;

import com.morcinek.showcase.general.network.NetworkCallback;
import com.morcinek.showcase.general.network.api.ApiService;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.general.network.response.ProgressController;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public abstract class NetworkRequester<T> {

    protected final ApiService apiService;

    protected NetworkCallback<T> networkCallback;

    public NetworkRequester(ApiService apiService) {
        this.apiService = apiService;
    }

    public void initialize(NetworkResponseListener<T> responseListener, ProgressController... progressControllers) {
        networkCallback = new NetworkCallback<>(responseListener, progressControllers);
    }

    public void cancelRequest() {
        networkCallback.cancel();
    }
}

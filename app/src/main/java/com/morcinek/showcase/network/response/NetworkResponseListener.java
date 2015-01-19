package com.morcinek.showcase.network.response;


import retrofit.RetrofitError;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public interface NetworkResponseListener<T> {

    public void success(T object);

    public void failure(RetrofitError error);
}
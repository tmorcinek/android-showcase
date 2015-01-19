package com.morcinek.showcase.network.error;

import retrofit.RetrofitError;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public interface ErrorHandler {

    public void handleError(RetrofitError networkError);
}

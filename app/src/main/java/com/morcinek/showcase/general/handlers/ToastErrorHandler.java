package com.morcinek.showcase.general.handlers;

import android.content.Context;
import android.widget.Toast;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.network.error.ErrorHandler;

import retrofit.RetrofitError;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class ToastErrorHandler implements ErrorHandler {

    private Context context;

    public ToastErrorHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleError(RetrofitError networkError) {
        showToastWithText(R.string.server_error_message);
    }

    public void showToastWithText(int stringResourceId) {
        Toast.makeText(context, stringResourceId, Toast.LENGTH_LONG).show();
    }
}

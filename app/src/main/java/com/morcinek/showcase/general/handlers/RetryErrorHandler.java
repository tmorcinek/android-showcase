package com.morcinek.showcase.general.handlers;

import android.app.Activity;
import android.app.Notification;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.network.error.ErrorHandler;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;

import retrofit.RetrofitError;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class RetryErrorHandler implements ErrorHandler {

    private final Activity activity;

    private ActionClickListener actionClickListener;

    private ViewGroup viewGroup;

    public RetryErrorHandler(Activity activity) {
        this.activity = activity;
    }

    public void registerAction(final Runnable retryAction) {
        actionClickListener = new ActionClickListener() {
            @Override
            public void onActionClicked(Snackbar snackbar) {
                retryAction.run();
            }
        };
    }

    public void registerViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void handleError(RetrofitError networkError) {
        SnackbarManager.show(
                Snackbar.with(activity)
                        .text(networkError.getLocalizedMessage())
                        .actionLabel(R.string.retry)
                        .actionListener(actionClickListener)
                , viewGroup);
    }
}

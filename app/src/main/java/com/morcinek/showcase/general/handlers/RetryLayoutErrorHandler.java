package com.morcinek.showcase.general.handlers;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.network.error.ErrorHandler;

import retrofit.RetrofitError;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class RetryLayoutErrorHandler implements ErrorHandler {

    private Activity activity;

    public RetryLayoutErrorHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void handleError(RetrofitError networkError) {
        activity.findViewById(R.id.retry_layout).setAnimation(prepareAnimationWithId(R.anim.slide_in_bottom));
        activity.findViewById(R.id.retry_layout).setVisibility(View.VISIBLE);
    }

    public void hideErrorMessage() {
        activity.findViewById(R.id.retry_layout).setAnimation(prepareAnimationWithId(R.anim.slide_out_bottom));
        activity.findViewById(R.id.retry_layout).setVisibility(View.GONE);
    }

    private Animation prepareAnimationWithId(int animId) {
        return AnimationUtils.loadAnimation(activity.getApplicationContext(), animId);
    }
}

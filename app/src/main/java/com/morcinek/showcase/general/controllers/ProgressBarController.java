package com.morcinek.showcase.general.controllers;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.network.response.ProgressController;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class ProgressBarController implements ProgressController {

    private Activity activity;

    private View contentLayout;
    private View progressBar;

    public ProgressBarController(Activity activity) {
        this.activity = activity;
        contentLayout = activity.findViewById(R.id.content_layout);
        progressBar = activity.findViewById(R.id.progress_bar);
    }

    @Override
    public void preExecute() {
        if (contentLayout.getVisibility() == View.VISIBLE) {
            contentLayout.setAnimation(prepareAnimationWithId(android.R.anim.fade_out));
            contentLayout.setVisibility(View.GONE);
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postExecuteWithSuccess(boolean success) {
        activity.findViewById(R.id.progress_bar).setVisibility(View.GONE);
        if (success) {
            contentLayout.setAnimation(prepareAnimationWithId(android.R.anim.fade_in));
            contentLayout.setVisibility(View.VISIBLE);
        }
    }

    private Animation prepareAnimationWithId(int animId) {
        return AnimationUtils.loadAnimation(activity, animId);
    }
}

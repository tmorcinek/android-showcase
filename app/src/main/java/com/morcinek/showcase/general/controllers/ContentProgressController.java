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
public class ContentProgressController implements ProgressController {

    private Activity activity;

    private View contentLayout;

    public ContentProgressController(Activity activity) {
        this.activity = activity;
        contentLayout = activity.findViewById(R.id.content_layout);
    }

    @Override
    public void preExecute() {
    }

    @Override
    public void postExecuteWithSuccess(boolean success) {
        if (success && contentLayout != null && contentLayout.getVisibility() == View.GONE) {
            contentLayout.setAnimation(prepareAnimationWithId(android.R.anim.fade_in));
            contentLayout.setVisibility(View.VISIBLE);
        }
    }

    private Animation prepareAnimationWithId(int animId) {
        return AnimationUtils.loadAnimation(activity, animId);
    }
}

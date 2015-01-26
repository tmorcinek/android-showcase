package com.morcinek.showcase.general.network;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.morcinek.showcase.R;
import com.morcinek.showcase.network.response.ProgressController;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class ProgressBarController implements ProgressController {

    private Activity activity;

    public ProgressBarController(Activity activity) {
        this.activity = activity;
    }

    private View getContentLayout() {
        return activity.findViewById(R.id.content_layout);
    }

    @Override
    public void preExecute() {
        View contentLayout = getContentLayout();
        if (contentLayout.getVisibility() == View.VISIBLE) {
            contentLayout.setAnimation(prepareAnimationWithId(android.R.anim.fade_out));
            contentLayout.setVisibility(View.GONE);
        }
        activity.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
    }

    @Override
    public void postExecuteWithSuccess(boolean success) {
        activity.findViewById(R.id.progress_bar).setVisibility(View.GONE);
        if (success) {
            View contentLayout = getContentLayout();
            contentLayout.setAnimation(prepareAnimationWithId(android.R.anim.fade_in));
            contentLayout.setVisibility(View.VISIBLE);
        }
    }

    private Animation prepareAnimationWithId(int animId) {
        return AnimationUtils.loadAnimation(activity.getApplicationContext(), animId);
    }
}

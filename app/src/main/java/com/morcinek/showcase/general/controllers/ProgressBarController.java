package com.morcinek.showcase.general.controllers;

import android.app.Activity;
import android.view.MenuItem;
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
    private MenuItem menuItem;

    public ProgressBarController(Activity activity) {
        this.activity = activity;
        contentLayout = activity.findViewById(R.id.content_layout);
        progressBar = activity.findViewById(R.id.progress_bar);
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public void preExecute() {
        if (contentLayout != null && contentLayout.getVisibility() == View.VISIBLE) {
            contentLayout.setAnimation(prepareAnimationWithId(android.R.anim.fade_out));
            contentLayout.setVisibility(View.GONE);
        }
        progressBar.setVisibility(View.VISIBLE);
        setMenuItemVisible(false);
    }

    @Override
    public void postExecuteWithSuccess(boolean success) {
        progressBar.setVisibility(View.GONE);
        if (contentLayout != null && success) {
            contentLayout.setAnimation(prepareAnimationWithId(android.R.anim.fade_in));
            contentLayout.setVisibility(View.VISIBLE);
        }
        setMenuItemVisible(true);
    }

    private void setMenuItemVisible(boolean success) {
        if (menuItem != null) {
            menuItem.setVisible(success);
        }
    }


    private Animation prepareAnimationWithId(int animId) {
        return AnimationUtils.loadAnimation(activity, animId);
    }
}

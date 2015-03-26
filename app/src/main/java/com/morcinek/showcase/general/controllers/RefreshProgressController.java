package com.morcinek.showcase.general.controllers;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.network.response.ProgressController;


/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class RefreshProgressController implements ProgressController {

    private SwipeRefreshLayout swipeRefreshLayout;

    public RefreshProgressController(Activity activity) {
        this.swipeRefreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public void preExecute() {
        updateSwipeRefreshLayout(true);
    }

    @Override
    public void postExecuteWithSuccess(boolean success) {
        updateSwipeRefreshLayout(false);
    }

    private void updateSwipeRefreshLayout(final boolean started) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(started);
            }
        });
    }
}

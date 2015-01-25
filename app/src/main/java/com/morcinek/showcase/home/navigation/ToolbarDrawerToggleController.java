package com.morcinek.showcase.home.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.helpers.UIHelper;
import com.morcinek.showcase.general.navigation.ToolbarHost;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class ToolbarDrawerToggleController extends ActionBarDrawerToggle {

    private final ActionBarActivity activity;

    private final Toolbar toolbar;

    public ToolbarDrawerToggleController(ActionBarActivity activity, DrawerLayout drawerLayout, Toolbar toolbar) {
        super(activity, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        this.activity = activity;
        this.toolbar = toolbar;
    }

    private int getToolbarDefaultColor() {
        return R.color.primaryColor;
    }

    private int getToolbarDefaultTitle() {
        return R.string.app_name;
    }

    private Fragment getCurrentFragment() {
        return activity.getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        activity.setTitle(getToolbarDefaultTitle());
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        ToolbarHost toolbarUpdater = (ToolbarHost) getCurrentFragment();
        if (toolbarUpdater != null) {
            activity.setTitle(toolbarUpdater.getTitle());
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);
        ToolbarHost toolbarUpdater = (ToolbarHost) getCurrentFragment();
        if (toolbarUpdater != null && toolbarUpdater.getColor() != null) {
            int fragmentToolbarColor = activity.getResources().getColor(toolbarUpdater.getColor());
            int defaultToolbarColor = activity.getResources().getColor(getToolbarDefaultColor());
            UIHelper.updateViewColor(toolbar, fragmentToolbarColor, defaultToolbarColor, slideOffset);
        }
    }
}

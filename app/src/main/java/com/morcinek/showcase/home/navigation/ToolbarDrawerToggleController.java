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

    private int getToolbarDefaultColorResourceId() {
        return R.color.primaryColor;
    }

    private int getToolbarDefaultTitleResourceId() {
        return R.string.app_name;
    }

    private Fragment getCurrentFragment() {
        return activity.getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        activity.setTitle(getToolbarDefaultTitleResourceId());
        toolbar.setBackgroundColor(getToolbarDefaultColor());
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        ToolbarHost toolbarUpdater = (ToolbarHost) getCurrentFragment();
        if (toolbarUpdater != null) {
            activity.setTitle(toolbarUpdater.getTitle());
            if (toolbarUpdater.getColor() != null) {
                toolbar.setBackgroundColor(getToolbarHostColor(toolbarUpdater));
            } else {
                toolbar.setBackgroundColor(getToolbarDefaultColor());
            }
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);
        ToolbarHost toolbarHost = (ToolbarHost) getCurrentFragment();
        if (toolbarHost != null && toolbarHost.getColor() != null) {
            UIHelper.updateViewColor(toolbar, getToolbarHostColor(toolbarHost), getToolbarDefaultColor(), slideOffset);
        }
    }

    private int getToolbarHostColor(ToolbarHost toolbarUpdater) {
        return activity.getResources().getColor(toolbarUpdater.getColor());
    }

    private int getToolbarDefaultColor() {
        return activity.getResources().getColor(getToolbarDefaultColorResourceId());
    }
}

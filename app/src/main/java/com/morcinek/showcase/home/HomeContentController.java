package com.morcinek.showcase.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.morcinek.showcase.R;
import com.morcinek.showcase.home.navigation.ToolbarHost;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class HomeContentController {

    private FragmentActivity activity;

    public HomeContentController(FragmentActivity activity) {
        this.activity = activity;
    }

    public void addFragment(Fragment fragment) {
        addFragment(fragment, false);
    }

    public void addFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(((ToolbarHost) fragment).getTitle());
        }
        fragmentTransaction.commit();
    }
}

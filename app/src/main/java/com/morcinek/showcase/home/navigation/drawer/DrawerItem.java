package com.morcinek.showcase.home.navigation.drawer;


import android.support.v4.app.Fragment;

import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class DrawerItem {

    @Getter
    private int title;

    @Getter
    private Integer icon;

    @Getter
    private Fragment fragment;

    public DrawerItem(int title, int icon, Fragment fragment) {
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }

    public DrawerItem(int title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}

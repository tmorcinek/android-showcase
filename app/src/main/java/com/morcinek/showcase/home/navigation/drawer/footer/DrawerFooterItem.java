package com.morcinek.showcase.home.navigation.drawer.footer;

import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class DrawerFooterItem {

    @Getter
    private int title;

    @Getter
    private Integer icon;

    public DrawerFooterItem(int title, int icon) {
        this.title = title;
        this.icon = icon;
    }
}

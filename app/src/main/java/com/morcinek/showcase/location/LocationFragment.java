package com.morcinek.showcase.location;

import com.google.android.gms.maps.SupportMapFragment;
import com.morcinek.showcase.R;
import com.morcinek.showcase.home.navigation.ToolbarHost;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class LocationFragment extends SupportMapFragment implements ToolbarHost{

    @Override
    public int getColor() {
        return R.color.accentColor;
    }

    @Override
    public String getTitle() {
        return getString(R.string.location_title);
    }
}

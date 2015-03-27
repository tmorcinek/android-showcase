package com.morcinek.showcase.home.navigation;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.dagger.components.ShowcaseFragment;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public abstract class ToolbarHostFragment extends ShowcaseFragment implements ToolbarHost {

    @Override
    public int getColor() {
        return R.color.primary;
    }
}

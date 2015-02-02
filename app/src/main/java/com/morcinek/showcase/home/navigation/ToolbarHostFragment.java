package com.morcinek.showcase.home.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.dagger.components.ShowcaseActivity;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public abstract class ToolbarHostFragment extends Fragment implements ToolbarHost {

    protected abstract int getLayoutResourceId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ShowcaseActivity) getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceId(), null);
    }

    @Override
    public int getColor() {
        return R.color.primaryColor;
    }
}

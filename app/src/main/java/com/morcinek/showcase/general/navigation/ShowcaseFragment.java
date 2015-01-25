package com.morcinek.showcase.general.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morcinek.showcase.dagger.activity.ShowcaseActivity;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public abstract class ShowcaseFragment extends Fragment implements ToolbarHost {

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
    public Integer getColor() {
        return null;
    }
}

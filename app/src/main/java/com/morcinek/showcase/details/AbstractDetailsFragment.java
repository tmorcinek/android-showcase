package com.morcinek.showcase.details;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.morcinek.showcase.general.dagger.components.ShowcaseFragment;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public abstract class AbstractDetailsFragment<T extends Parcelable> extends ShowcaseFragment {

    private T data;

    protected final T getData() {
        if (data == null && getArguments() != null) {
            data = getArguments().getParcelable(Object.class.getName());
        }
        return data;
    }

    protected abstract String getTitle();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getTitle());
    }

    protected void setupTextViewWithText(View view, int resourceId, String text) {
        ((TextView) view.findViewById(resourceId)).setText(text);
    }
}

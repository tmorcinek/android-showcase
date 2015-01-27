package com.morcinek.showcase.dagger.components;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.morcinek.showcase.ShowcaseApplication;
import com.morcinek.showcase.dagger.configuration.ActivityModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ShowcaseActivity extends ActionBarActivity {

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityGraph = getShowcaseApplication().getApplicationGraph().plus(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        activityGraph = null;
        super.onDestroy();
    }

    private ShowcaseApplication getShowcaseApplication() {
        return (ShowcaseApplication) getApplication();
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }

    public void inject(Object object) {
        activityGraph.inject(object);
    }
}

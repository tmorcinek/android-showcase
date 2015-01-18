package com.morcinek.showcase;

import android.app.Application;

import com.morcinek.showcase.dagger.configuration.ApplicationModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ShowcaseApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ApplicationModule(this));
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public ObjectGraph getApplicationGraph() {
        return objectGraph;
    }
}


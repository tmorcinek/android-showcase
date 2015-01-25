package com.morcinek.showcase.dagger.configuration;

import android.content.Context;

import com.morcinek.showcase.author.AuthorFragment;
import com.morcinek.showcase.dagger.activity.ShowcaseActivity;
import com.morcinek.showcase.general.ToastErrorHandler;
import com.morcinek.showcase.home.HomeActivity;
import com.morcinek.showcase.network.NetworkFacade;
import com.morcinek.showcase.network.ProductionNetworkFacade;
import com.morcinek.showcase.network.api.ApiService;
import com.morcinek.showcase.network.error.ErrorHandler;
import com.morcinek.showcase.network.model.Author;
import com.morcinek.showcase.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
@Module(
        injects = {
                HomeActivity.class,
                AuthorFragment.class,
        },
        addsTo = ApplicationModule.class,
        library = true
)
public class ActivityModule {

    private final ShowcaseActivity showcaseActivity;

    public ActivityModule(ShowcaseActivity showcaseActivity) {
        this.showcaseActivity = showcaseActivity;
    }

    @Provides
    ErrorHandler provideErrorHandler() {
        return new ToastErrorHandler(showcaseActivity);
    }
}

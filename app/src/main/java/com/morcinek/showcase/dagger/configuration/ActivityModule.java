package com.morcinek.showcase.dagger.configuration;

import com.morcinek.showcase.author.AuthorFragment;
import com.morcinek.showcase.dagger.components.ShowcaseActivity;
import com.morcinek.showcase.education.EducationListFragment;
import com.morcinek.showcase.general.network.ProgressBarController;
import com.morcinek.showcase.general.network.RefreshProgressController;
import com.morcinek.showcase.general.network.RetryLayoutErrorHandler;
import com.morcinek.showcase.general.network.ToastErrorHandler;
import com.morcinek.showcase.home.HomeActivity;
import com.morcinek.showcase.network.error.ErrorHandler;

import dagger.Module;
import dagger.Provides;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
@Module(
        injects = {
                HomeActivity.class,
                AuthorFragment.class,
                EducationListFragment.class,
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

    @Provides
    RetryLayoutErrorHandler provideRetryLayoutErrorHandler() {
        return new RetryLayoutErrorHandler(showcaseActivity);
    }

    @Provides
    ProgressBarController provideProgressBarController() {
        return new ProgressBarController(showcaseActivity);
    }

    @Provides
    RefreshProgressController provideRefreshProgressController() {
        return new RefreshProgressController(showcaseActivity);
    }
}

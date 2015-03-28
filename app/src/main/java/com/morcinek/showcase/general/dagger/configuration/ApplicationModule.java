package com.morcinek.showcase.general.dagger.configuration;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.morcinek.showcase.BuildConfig;
import com.morcinek.showcase.details.provider.DetailsFragmentProvider;
import com.morcinek.showcase.details.provider.FragmentsProvider;
import com.morcinek.showcase.general.network.api.ApiService;
import com.morcinek.showcase.general.network.requesters.AuthorRequester;
import com.morcinek.showcase.general.network.requesters.EducationsRequester;
import com.morcinek.showcase.general.network.requesters.ExperienceRequester;
import com.morcinek.showcase.general.network.requesters.LocationRequester;
import com.morcinek.showcase.general.network.requesters.SkillsRequester;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
@Module(library = true)
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BuildConfig.API_ENDPOINT)
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("Retrofit"))  // just for debugging purposes
                .build();
        return restAdapter.create(ApiService.class);
    }

    @Provides
    AuthorRequester provideAuthorRequester(ApiService apiService) {
        return new AuthorRequester(apiService);
    }

    @Provides
    EducationsRequester provideEducationsRequester(ApiService apiService) {
        return new EducationsRequester(apiService);
    }

    @Provides
    ExperienceRequester provideExperienceRequester(ApiService apiService) {
        return new ExperienceRequester(apiService);
    }

    @Provides
    SkillsRequester provideSkillsRequester(ApiService apiService) {
        return new SkillsRequester(apiService);
    }

    @Provides
    LocationRequester provideLocationRequester(ApiService apiService) {
        return new LocationRequester(apiService);
    }
}

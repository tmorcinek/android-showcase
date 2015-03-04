package com.morcinek.showcase.general.dagger.configuration;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.morcinek.showcase.BuildConfig;
import com.morcinek.showcase.general.network.NetworkFacade;
import com.morcinek.showcase.general.network.ProductionNetworkFacade;
import com.morcinek.showcase.general.network.api.ApiService;
import com.morcinek.showcase.general.network.requesters.AuthorRequester;

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
    @Singleton
    NetworkFacade provideNetworkFacade(ApiService apiService) {
        return new ProductionNetworkFacade(apiService);
    }

    @Provides
    AuthorRequester provideAuthorRequester(ApiService apiService) {
        return new AuthorRequester(apiService);
    }
}

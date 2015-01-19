package com.morcinek.showcase.network;


import com.morcinek.showcase.network.api.ApiService;
import com.morcinek.showcase.network.model.Author;
import com.morcinek.showcase.network.model.Education;
import com.morcinek.showcase.network.model.Experience;
import com.morcinek.showcase.network.model.Skill;
import com.morcinek.showcase.network.response.NetworkResponseListener;
import com.morcinek.showcase.network.response.ProgressController;

import java.util.List;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class ProductionNetworkFacade implements NetworkFacade {

    private ApiService apiService;

    public ProductionNetworkFacade(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getAuthor(NetworkResponseListener<Author> responseListener, ProgressController... progressControllers) {
        apiService.getAuthor(new NetworkCallback<>(responseListener, progressControllers));
    }

    @Override
    public void getEducation(NetworkResponseListener<List<Education>> responseListener, ProgressController... progressControllers) {
        apiService.getEducation(new NetworkCallback<>(responseListener, progressControllers));
    }

    @Override
    public void getExperience(NetworkResponseListener<List<Experience>> responseListener, ProgressController... progressControllers) {
        apiService.getExperience(new NetworkCallback<>(responseListener, progressControllers));
    }

    @Override
    public void getSkills(NetworkResponseListener<List<Skill>> responseListener, ProgressController... progressControllers) {
        apiService.getSkills(new NetworkCallback<>(responseListener, progressControllers));
    }
}

package com.morcinek.showcase.general.network;


import com.morcinek.showcase.general.network.api.ApiService;
import com.morcinek.showcase.author.model.Author;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.experience.model.Experience;
import com.morcinek.showcase.location.model.Location;
import com.morcinek.showcase.skills.model.Skill;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.general.network.response.ProgressController;

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

    @Override
    public void getLocation(NetworkResponseListener<Location> responseListener, ProgressController... progressControllers) {
        apiService.getLocation(new NetworkCallback<>(responseListener, progressControllers));
    }
}

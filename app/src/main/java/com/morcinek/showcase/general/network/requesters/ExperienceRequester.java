package com.morcinek.showcase.general.network.requesters;

import com.morcinek.showcase.experience.model.Experience;
import com.morcinek.showcase.general.network.api.ApiService;

import java.util.List;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class ExperienceRequester extends NetworkRequester<List<Experience>> {

    public ExperienceRequester(ApiService apiService) {
        super(apiService);
    }

    public void requestExperience() {
        apiService.getExperience(provideCallback());
    }
}

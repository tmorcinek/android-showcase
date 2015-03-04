package com.morcinek.showcase.general.network.requesters;

import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.general.network.api.ApiService;

import java.util.List;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class EducationsRequester extends NetworkRequester<List<Education>> {

    public EducationsRequester(ApiService apiService) {
        super(apiService);
    }

    public void requestEducations() {
        apiService.getEducation(networkCallback);
    }
}

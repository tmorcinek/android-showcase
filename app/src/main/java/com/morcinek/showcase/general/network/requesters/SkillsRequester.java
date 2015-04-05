package com.morcinek.showcase.general.network.requesters;

import com.morcinek.showcase.general.network.api.ApiService;
import com.morcinek.showcase.skills.model.Skill;

import java.util.List;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class SkillsRequester extends NetworkRequester<List<Skill>> {

    public SkillsRequester(ApiService apiService) {
        super(apiService);
    }

    public void requestSkills() {
        apiService.getSkills(provideCallback());
    }
}

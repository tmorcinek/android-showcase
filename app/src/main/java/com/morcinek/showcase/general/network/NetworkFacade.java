package com.morcinek.showcase.general.network;


import com.morcinek.showcase.author.model.Author;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.experience.model.Experience;
import com.morcinek.showcase.skills.model.Skill;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.general.network.response.ProgressController;

import java.util.List;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public interface NetworkFacade {

    public void getAuthor(final NetworkResponseListener<Author> responseListener,
                          ProgressController... progressControllers);

    public void getEducation(final NetworkResponseListener<List<Education>> responseListener,
                             ProgressController... progressControllers);

    public void getExperience(final NetworkResponseListener<List<Experience>> responseListener,
                              ProgressController... progressControllers);

    public void getSkills(final NetworkResponseListener<List<Skill>> responseListener,
                          ProgressController... progressControllers);
}

package com.morcinek.showcase.general.network.api;

import com.morcinek.showcase.author.model.Author;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.experience.model.Experience;
import com.morcinek.showcase.location.model.Location;
import com.morcinek.showcase.skills.model.Skill;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public interface ApiService {

    @GET("/author")
    public void getAuthor(Callback<Author> callback);

    @GET("/education")
    public void getEducation(Callback<List<Education>> callback);

    @GET("/experience")
    public void getExperience(Callback<List<Experience>> callback);

    @GET("/skills")
    public void getSkills(Callback<List<Skill>> callback);

    @GET("/location")
    public void getLocation(Callback<Location> callback);
}

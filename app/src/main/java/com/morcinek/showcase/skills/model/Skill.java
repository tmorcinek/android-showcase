package com.morcinek.showcase.skills.model;

import com.morcinek.showcase.general.network.model.Link;

import hrisey.Parcelable;
import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Parcelable
public class Skill {

    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private Link link;
}

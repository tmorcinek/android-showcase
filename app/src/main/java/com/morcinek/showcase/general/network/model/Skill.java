package com.morcinek.showcase.general.network.model;

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

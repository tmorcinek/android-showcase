package com.morcinek.showcase.experience.model;

import com.morcinek.showcase.general.network.model.Link;

import hrisey.Parcelable;
import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Parcelable
public class Experience implements android.os.Parcelable {

    @Getter
    private String companyName;

    @Getter
    private String position;

    @Getter
    private String dates;

    @Getter
    private String description;

    @Getter
    private Link link;
}

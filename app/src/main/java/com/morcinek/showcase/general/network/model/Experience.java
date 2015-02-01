package com.morcinek.showcase.general.network.model;

import hrisey.Parcelable;
import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Parcelable
public class Experience {

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

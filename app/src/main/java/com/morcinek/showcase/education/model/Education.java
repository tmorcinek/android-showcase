package com.morcinek.showcase.education.model;

import com.morcinek.showcase.general.network.model.Link;

import hrisey.Parcelable;
import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Parcelable
public class Education implements android.os.Parcelable {

    @Getter
    private String university;

    @Getter
    private String speciality;

    @Getter
    private String description;

    @Getter
    private String dates;

    @Getter
    private Link link;
}
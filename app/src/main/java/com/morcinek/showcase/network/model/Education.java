package com.morcinek.showcase.network.model;

import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class Education {

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
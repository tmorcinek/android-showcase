package com.morcinek.showcase.general.network.model;

import java.util.List;

import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class Author {

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private String email;

    @Getter
    private List<String> telephones;

    @Getter
    private List<Link> links;
}

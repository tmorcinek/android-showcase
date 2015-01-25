package com.morcinek.showcase.network.model;

import java.util.List;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class Author {

    private String name;
    private String description;
    private String email;
    private List<String> telephones;
    private List<Link> links;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public List<Link> getLinks() {
        return links;
    }
}

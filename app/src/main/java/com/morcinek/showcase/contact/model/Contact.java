package com.morcinek.showcase.contact.model;

import hrisey.Parcelable;
import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Parcelable
public class Contact implements android.os.Parcelable {

    @Getter
    private String name;

    @Getter
    private String content;

    @Getter
    private Type type;

    @Getter
    private boolean active;

    public enum Type {
        Phone, Email, Skype, Line, Website, Play
    }
}



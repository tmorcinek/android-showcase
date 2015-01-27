package com.morcinek.showcase.general.network.model;

import hrisey.Parcelable;
import lombok.Getter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
@Parcelable
public class Link implements android.os.Parcelable {

    @Getter
    private String name;

    @Getter
    private String url;
}

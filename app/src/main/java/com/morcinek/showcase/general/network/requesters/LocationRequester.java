package com.morcinek.showcase.general.network.requesters;

import com.morcinek.showcase.general.network.api.ApiService;
import com.morcinek.showcase.location.model.Location;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class LocationRequester extends NetworkRequester<Location> {

    public LocationRequester(ApiService apiService) {
        super(apiService);
    }

    public void requestLocation() {
        apiService.getLocation(networkCallback);
    }
}

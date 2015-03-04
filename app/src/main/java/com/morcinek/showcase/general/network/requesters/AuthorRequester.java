package com.morcinek.showcase.general.network.requesters;

import com.morcinek.showcase.author.model.Author;
import com.morcinek.showcase.general.network.api.ApiService;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class AuthorRequester extends NetworkRequester<Author> {

    public AuthorRequester(ApiService apiService) {
        super(apiService);
    }

    public void requestAuthor() {
        apiService.getAuthor(networkCallback);
    }
}

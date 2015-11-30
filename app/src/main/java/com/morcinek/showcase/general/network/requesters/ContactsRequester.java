package com.morcinek.showcase.general.network.requesters;

import com.morcinek.showcase.contact.model.Contact;
import com.morcinek.showcase.general.network.api.ApiService;

import java.util.List;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class ContactsRequester extends NetworkRequester<List<Contact>> {

    public ContactsRequester(ApiService apiService) {
        super(apiService);
    }

    public void requestContacts() {
        apiService.getContacts(provideCallback());
    }
}

package com.morcinek.showcase.contact;

import android.support.v7.widget.RecyclerView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.contact.model.Contact;
import com.morcinek.showcase.general.AbstractListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.network.requesters.ContactsRequester;

import javax.inject.Inject;

import lombok.Getter;

public class ContactListFragment extends AbstractListFragment<Contact> {

    @Getter
    @Inject
    ContactsRequester networkRequester;

    @Override
    protected AbstractRecyclerViewAdapter<Contact, ? extends RecyclerView.ViewHolder> getCreateListAdapter() {
        return new ContactListAdapter(getActivity());
    }

    @Override
    public String getTitle() {
        return getString(R.string.contact_title);
    }

    @Override
    public void onRefresh() {
        networkRequester.requestContacts();
    }
}

package com.morcinek.showcase.contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.contact.model.Contact;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;


/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class ContactListAdapter extends AbstractRecyclerViewAdapter<Contact, ContactListAdapter.ViewHolder> {

    public ContactListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Contact contact = getItem(i);
        initializeOnClickListener(viewHolder, contact);
        viewHolder.title.setText(getTitle(contact));
        viewHolder.subtitle.setText(contact.getName());
        viewHolder.icon.setImageResource(getIcon(contact.getType()));
    }

    private String getTitle(Contact contact) {
        switch (contact.getType()) {
            case Website:
            case Linkedin:
            case Github:
                return contact.getContent().replaceFirst("https?://", "");
            case Play:
                return context.getString(R.string.action_play_title);
            default:
                return contact.getContent();
        }
    }

    private int getIcon(Contact.Type contactType) {
        switch (contactType) {
            case Phone:
                return R.drawable.ic_action_call;
            case Email:
                return R.drawable.ic_action_email;
            case Skype:
                return R.drawable.ic_action_skype;
            case Line:
                return R.drawable.ic_action_line;
            case Play:
                return R.drawable.ic_action_play;
            case Website:
                return R.drawable.ic_action_web_site;
            case Github:
                return R.drawable.ic_action_github;
            case Linkedin:
                return R.drawable.ic_action_linkedin;
            default:
                return 0;
        }
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView subtitle;
        private final ImageView icon;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            subtitle = (TextView) view.findViewById(R.id.subtitle);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }
}

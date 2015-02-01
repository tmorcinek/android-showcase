package com.morcinek.showcase.experience;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.network.model.Experience;


/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class ExperienceListAdapter extends AbstractRecyclerViewAdapter<Experience, ExperienceListAdapter.ViewHolder> {

    public ExperienceListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.experience_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Experience education = getItem(i);
        initializeOnClickListener(viewHolder, education);
        viewHolder.companyView.setText(education.getCompanyName());
        viewHolder.positionView.setText(education.getPosition());
        viewHolder.datesView.setText(education.getDates());
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView companyView;
        private final TextView positionView;
        private final TextView datesView;

        public ViewHolder(View view) {
            super(view);
            companyView = (TextView) view.findViewById(R.id.company);
            positionView = (TextView) view.findViewById(R.id.position);
            datesView = (TextView) view.findViewById(R.id.dates);
        }
    }
}

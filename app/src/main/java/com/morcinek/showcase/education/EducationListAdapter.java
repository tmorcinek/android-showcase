package com.morcinek.showcase.education;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.network.model.Education;


/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class EducationListAdapter extends AbstractRecyclerViewAdapter<Education, EducationListAdapter.ViewHolder> {

    public EducationListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.education_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Education education = getItem(i);
        initializeOnClickListener(viewHolder, education);
        viewHolder.titleView.setText(education.getUniversity());
        viewHolder.subtitleView.setText(education.getDates());
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;
        private final TextView subtitleView;

        public ViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.title);
            subtitleView = (TextView) view.findViewById(R.id.subtitle);
        }
    }
}

package com.morcinek.showcase.skills;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.skills.model.Skill;


/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class SkillsListAdapter extends AbstractRecyclerViewAdapter<Skill, SkillsListAdapter.ViewHolder> {

    public SkillsListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.skills_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Skill education = getItem(i);
        initializeOnClickListener(viewHolder, education);
        viewHolder.titleView.setText(education.getTitle());
        viewHolder.subtitleView.setText(education.getDescription());
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

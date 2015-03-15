package com.morcinek.showcase.details;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.morcinek.showcase.R;
import com.morcinek.showcase.education.details.EducationDetailsFragment;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.general.dagger.components.ShowcaseActivity;
import com.morcinek.showcase.home.HomeContentController;

import javax.inject.Inject;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class DetailsActivity extends ShowcaseActivity implements View.OnClickListener {

    @Inject
    HomeContentController homeContentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_outside_right);
        setContentView(R.layout.details);

        setupToolbar();
        String className = getIntent().getStringExtra(Class.class.getName());
        if (className.equals(EducationDetailsFragment.class.getName())) {
            setupEducationDetailsFragment();
        }
    }

    private void setupEducationDetailsFragment() {
        Education education = getIntent().getParcelableExtra(Object.class.getName());
        getSupportActionBar().setTitle(education.getUniversity());
        homeContentController.addFragment(EducationDetailsFragment.newInstance(education));
    }

    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_inside_left, R.anim.slide_out_right);
    }
}

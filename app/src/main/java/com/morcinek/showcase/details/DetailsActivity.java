package com.morcinek.showcase.details;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.morcinek.showcase.R;
import com.morcinek.showcase.education.details.EducationDetailsFragment;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.experience.details.ExperienceDetailsFragment;
import com.morcinek.showcase.experience.model.Experience;
import com.morcinek.showcase.general.dagger.components.ShowcaseActivity;
import com.morcinek.showcase.home.HomeContentController;

import javax.inject.Inject;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class DetailsActivity extends ShowcaseActivity {

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
        } else if (className.equals(ExperienceDetailsFragment.class.getName())) {
            setupExperienceDetailsFragment();
        }
    }

    private void setupEducationDetailsFragment() {
        Education education = getIntent().getParcelableExtra(Object.class.getName());
        getSupportActionBar().setTitle(education.getUniversity());
        homeContentController.addFragment(EducationDetailsFragment.newInstance(education));
    }

    private void setupExperienceDetailsFragment() {
        Experience experience = getIntent().getParcelableExtra(Object.class.getName());
        getSupportActionBar().setTitle(experience.getCompanyName());
        homeContentController.addFragment(ExperienceDetailsFragment.newInstance(experience));
    }

    private void setupToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_inside_left, R.anim.slide_out_right);
    }
}

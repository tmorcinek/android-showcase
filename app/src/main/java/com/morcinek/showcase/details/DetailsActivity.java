package com.morcinek.showcase.details;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
        overridePendingTransition(R.anim.slide_in_top, android.R.anim.fade_out);
        setContentView(R.layout.home);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        setupToolbar();
        String className = getIntent().getStringExtra(Class.class.getName());
        if (className.equals(EducationDetailsFragment.class.getName())) {
            Education education = getIntent().getParcelableExtra(Object.class.getName());
            homeContentController.addFragment(EducationDetailsFragment.newInstance(education));
        }
    }

    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}

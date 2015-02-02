package com.morcinek.showcase.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.dagger.components.ShowcaseActivity;
import com.morcinek.showcase.home.navigation.ToolbarDrawerToggleController;
import com.morcinek.showcase.home.navigation.drawer.DrawerController;

import javax.inject.Inject;

public class HomeActivity extends ShowcaseActivity {

    @Inject
    HomeContentController homeContentController;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_top, android.R.anim.fade_out);
        setContentView(R.layout.home);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerToggle = new ToolbarDrawerToggleController(this, drawerLayout, toolbar);
        drawerLayout.setDrawerListener(drawerToggle);

        DrawerController drawerController = new DrawerController(this, homeContentController, drawerLayout);
        if (savedInstanceState == null) {
            drawerController.showDefaultFragment();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}

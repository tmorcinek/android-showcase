package com.morcinek.showcase.home.navigation.drawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.author.AuthorFragment;
import com.morcinek.showcase.education.EducationListFragment;
import com.morcinek.showcase.experience.ExperienceListFragment;
import com.morcinek.showcase.general.dagger.components.ShowcaseActivity;
import com.morcinek.showcase.skills.SkillsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class DrawerController implements AdapterView.OnItemClickListener {

    private final ShowcaseActivity activity;
    private final ListView drawerListView;
    private final DrawerLayout drawerLayout;
    private final DrawerListAdapter drawerListAdapter;

    public DrawerController(ShowcaseActivity activity, DrawerLayout drawerLayout) {
        this.activity = activity;
        this.drawerLayout = drawerLayout;
        this.drawerListView = (ListView) activity.findViewById(R.id.left_drawer);

        drawerListAdapter = new DrawerListAdapter(this.activity);
        drawerListAdapter.setList(prepareDrawerItemList());
        drawerListView.setAdapter(drawerListAdapter);
        drawerListView.setOnItemClickListener(this);
    }

    private List<DrawerItem> prepareDrawerItemList() {
        ArrayList<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(R.string.author_title, new AuthorFragment()));
        drawerItems.add(new DrawerItem(R.string.education_list_title, new EducationListFragment()));
        drawerItems.add(new DrawerItem(R.string.experience_list_title, new ExperienceListFragment()));
        drawerItems.add(new DrawerItem(R.string.skills_list_title, new SkillsListFragment()));
        return drawerItems;
    }

    private void selectItemAtPosition(int position) {
        Fragment fragment = getFragmentAtPosition(position);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        drawerListView.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerListView);
    }

    private Fragment getFragmentAtPosition(int position) {
        return drawerListAdapter.getItem(position).getFragment();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItemAtPosition(position);
    }

    public void showDefaultFragment() {
        selectItemAtPosition(0);
    }

    public void addFragmentWithObject(Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }
}
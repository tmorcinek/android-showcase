package com.morcinek.showcase.home.navigation.drawer;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mikepenz.aboutlibraries.LibsBuilder;
import com.morcinek.showcase.R;
import com.morcinek.showcase.author.AuthorFragment;
import com.morcinek.showcase.contact.ContactListFragment;
import com.morcinek.showcase.education.EducationListFragment;
import com.morcinek.showcase.experience.ExperienceListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.home.HomeContentController;
import com.morcinek.showcase.home.navigation.drawer.footer.DrawerFooterAdapter;
import com.morcinek.showcase.home.navigation.drawer.footer.DrawerFooterItem;
import com.morcinek.showcase.location.LocationFragment;
import com.morcinek.showcase.skills.SkillsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class DrawerController implements AdapterView.OnItemClickListener, AbstractRecyclerViewAdapter.OnItemClickListener<DrawerFooterItem> {

    private static final int HIDE_DRAWER_LIST_DELAY_MILLIS = 150;

    private FragmentActivity activity;
    private final HomeContentController homeContentController;
    private final DrawerLayout drawerLayout;

    private final ListView drawerListView;
    private final View drawerContent;

    public DrawerController(FragmentActivity activity, HomeContentController homeContentController, DrawerLayout drawerLayout) {
        this.activity = activity;
        this.homeContentController = homeContentController;
        this.drawerLayout = drawerLayout;

        drawerContent = activity.findViewById(R.id.drawer_content);
        drawerListView = (ListView) drawerContent.findViewById(R.id.drawer_list);

        setupRecyclerView(activity);
        setupDrawerListAdapter(activity);
    }

    private void setupRecyclerView(FragmentActivity activity) {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.drawer_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(createSportMenuAdapter(activity));
    }

    private DrawerFooterAdapter createSportMenuAdapter(Activity activity) {
        DrawerFooterAdapter sportMenuAdapter = new DrawerFooterAdapter(activity);
        sportMenuAdapter.setItemClickListener(this);
        sportMenuAdapter.setList(prepareMenuDrawerItems());
        return sportMenuAdapter;
    }

    private List<DrawerFooterItem> prepareMenuDrawerItems() {
        ArrayList<DrawerFooterItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerFooterItem(R.string.rate_title, R.drawable.ic_star_black));
        drawerItems.add(new DrawerFooterItem(R.string.check_code_title, R.drawable.ic_description_black));
        drawerItems.add(new DrawerFooterItem(R.string.libraries_title, R.drawable.ic_info_black));
        return drawerItems;
    }

    private void setupDrawerListAdapter(FragmentActivity activity) {
        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(activity);
        drawerListAdapter.setList(prepareDrawerItemList());
        drawerListView.setAdapter(drawerListAdapter);
        drawerListView.setOnItemClickListener(this);
    }

    private List<DrawerItem> prepareDrawerItemList() {
        ArrayList<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(R.string.author_title, R.drawable.ic_account_circle_black, new AuthorFragment()));
        drawerItems.add(new DrawerItem(R.string.location_title, R.drawable.ic_room_black, new LocationFragment()));
        drawerItems.add(new DrawerItem(R.string.education_list_title, R.drawable.ic_school_black, new EducationListFragment()));
        drawerItems.add(new DrawerItem(R.string.experience_list_title, R.drawable.ic_local_library_black, new ExperienceListFragment()));
        drawerItems.add(new DrawerItem(R.string.skills_list_title, R.drawable.ic_stars_black, new SkillsListFragment()));
        drawerItems.add(new DrawerItem(R.string.contact_title, R.drawable.ic_contact_calendar_black, new ContactListFragment()));
        return drawerItems;
    }

    private void selectItemAtPosition(DrawerItem drawerItem, int position) {
        homeContentController.addFragment(drawerItem.getFragment());
        drawerListView.setItemChecked(position, true);
        hideDrawerWithDelay();
    }

    private void hideDrawerWithDelay() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                drawerLayout.closeDrawer(drawerContent);
            }
        }, HIDE_DRAWER_LIST_DELAY_MILLIS);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItemAtPosition((DrawerItem) parent.getItemAtPosition(position), position);
    }

    public void showDefaultFragment() {
        selectItemAtPosition((DrawerItem) drawerListView.getItemAtPosition(0), 0);
    }

    @Override
    public void onItemClicked(DrawerFooterItem item) {
        switch (item.getTitle()) {
            case R.string.rate_title:
                break;
            case R.string.check_code_title:
                break;
            case R.string.libraries_title:
                new LibsBuilder().
                        withFields(R.string.class.getFields())
                        .withActivityTheme(R.style.BaseTheme)
                        .withActivityTitle(activity.getString(R.string.libraries_title))
                        .start(activity);
                break;
        }
    }
}
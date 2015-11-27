package com.morcinek.showcase.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.morcinek.showcase.R;
import com.morcinek.showcase.general.controllers.ProgressBarController;
import com.morcinek.showcase.general.dagger.components.ShowcaseActivity;
import com.morcinek.showcase.general.network.error.ErrorHandler;
import com.morcinek.showcase.general.network.requesters.LocationRequester;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.home.navigation.ToolbarHost;
import com.morcinek.showcase.location.model.Location;

import javax.inject.Inject;

import retrofit.RetrofitError;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class LocationFragment extends SupportMapFragment implements ToolbarHost, OnMapReadyCallback, NetworkResponseListener<Location> {

    @Inject
    LocationRequester locationRequester;

    @Inject
    ProgressBarController progressBarController;

    @Inject
    ErrorHandler errorHandler;

    @Override
    public int getColor() {
        return R.color.accent;
    }

    @Override
    public String getTitle() {
        return getString(R.string.location_title);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.location, menu);
        super.onCreateOptionsMenu(menu, inflater);
        progressBarController.setMenuItem(menu.findItem(R.id.action_refresh));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        progressBarController.setMenuItem(menu.findItem(R.id.action_refresh));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                onRefresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ShowcaseActivity) getActivity()).inject(this);
        locationRequester.initialize(this, progressBarController);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        onRefresh();
    }

    private void onRefresh() {
        locationRequester.requestLocation();
    }

    @Override
    public void success(Location object) {
        LatLng location = new LatLng(object.getLatitude(), object.getLongitude());

        GoogleMap map = getMap();
        map.setMyLocationEnabled(true);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 11));
        map.addMarker(new MarkerOptions()
                .title("I am here")
                .snippet("This is my current location.")
                .position(location));
    }

    @Override
    public void failure(RetrofitError error) {
        errorHandler.handleError(error);
    }

    @Override
    public void onDestroy() {
        locationRequester.cancelRequest();
        super.onDestroy();
    }
}

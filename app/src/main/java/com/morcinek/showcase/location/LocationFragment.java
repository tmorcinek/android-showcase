package com.morcinek.showcase.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.morcinek.showcase.general.network.NetworkFacade;
import com.morcinek.showcase.general.network.error.ErrorHandler;
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
    NetworkFacade networkFacade;

    @Inject
    ProgressBarController progressBarController;

    @Inject
    ErrorHandler errorHandler;

    @Override
    public int getColor() {
        return R.color.accentColor;
    }

    @Override
    public String getTitle() {
        return getString(R.string.location_title);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ShowcaseActivity) getActivity()).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        networkFacade.getLocation(this, progressBarController);
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
}

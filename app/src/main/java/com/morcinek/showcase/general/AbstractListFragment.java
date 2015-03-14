package com.morcinek.showcase.general;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.controllers.RefreshProgressController;
import com.morcinek.showcase.general.handlers.RetryLayoutErrorHandler;
import com.morcinek.showcase.general.network.requesters.NetworkRequester;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.home.HomeContentController;
import com.morcinek.showcase.home.navigation.ToolbarHostFragment;

import java.util.List;

import javax.inject.Inject;

import retrofit.RetrofitError;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public abstract class AbstractListFragment<T> extends ToolbarHostFragment implements NetworkResponseListener<List<T>>, SwipeRefreshLayout.OnRefreshListener, AbstractRecyclerViewAdapter.OnItemClickListener<T>, View.OnClickListener {

    @Inject
    protected RefreshProgressController progressController;

    @Inject
    protected HomeContentController homeContentController;

    @Inject
    RetryLayoutErrorHandler errorHandler;

    private AbstractRecyclerViewAdapter<T, ? extends RecyclerView.ViewHolder> listAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.default_list;
    }

    protected abstract AbstractRecyclerViewAdapter<T, ? extends RecyclerView.ViewHolder> getCreateListAdapter();

    protected abstract NetworkRequester<List<T>> getNetworkRequester();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.retry_layout).setOnClickListener(this);

        setupListAdapter();
        setupRecyclerView(view);
        setupSwipeRefreshLayout(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getNetworkRequester().initialize(this, progressController);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listAdapter.getItemCount() == 0) {
            onRefresh();
        }
    }

    private void setupListAdapter() {
        listAdapter = getCreateListAdapter();
        listAdapter.setItemClickListener(this);
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);
    }

    private void setupSwipeRefreshLayout(View view) {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accent));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void success(List<T> object) {
        errorHandler.hideErrorMessage();
        listAdapter.setList(object);
        invokeRecyclerViewAnimation();
    }

    private void invokeRecyclerViewAnimation() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        fadeInAnimation.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutAnimation(new LayoutAnimationController(fadeInAnimation));
        recyclerView.startLayoutAnimation();
    }

    @Override
    public void failure(RetrofitError error) {
        errorHandler.handleError(error);
    }

    @Override
    public void onClick(View v) {
        errorHandler.hideErrorMessage();
        onRefresh();
    }

    @Override
    public void onDestroy() {
        getNetworkRequester().cancelRequest();
        super.onDestroy();
    }
}

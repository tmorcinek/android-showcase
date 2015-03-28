package com.morcinek.showcase.general;

import android.os.Bundle;
import android.os.Parcelable;
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
import com.morcinek.showcase.details.provider.FragmentsProvider;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.controllers.RefreshProgressController;
import com.morcinek.showcase.general.handlers.RetryErrorHandler;
import com.morcinek.showcase.general.network.requesters.NetworkRequester;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.home.navigation.ToolbarHostFragment;

import java.util.List;

import javax.inject.Inject;

import retrofit.RetrofitError;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public abstract class AbstractListFragment<T extends Parcelable> extends ToolbarHostFragment implements NetworkResponseListener<List<T>>, SwipeRefreshLayout.OnRefreshListener, AbstractRecyclerViewAdapter.OnItemClickListener<T>, Runnable {

    @Inject
    protected RefreshProgressController progressController;

    @Inject
    RetryErrorHandler errorHandler;

    @Inject
    FragmentsProvider fragmentsProvider;

    private AbstractRecyclerViewAdapter<T, ? extends RecyclerView.ViewHolder> listAdapter;

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.default_list;
    }

    protected abstract AbstractRecyclerViewAdapter<T, ? extends RecyclerView.ViewHolder> getCreateListAdapter();

    protected abstract NetworkRequester<List<T>> getNetworkRequester();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupListAdapter();
        setupRecyclerView(view);
        setupSwipeRefreshLayout(view);

        getNetworkRequester().initialize(this, progressController);

        errorHandler.registerAction(this);
        errorHandler.registerViewGroup((android.view.ViewGroup) view);
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);
    }

    private void setupSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accent));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void success(List<T> object) {
        listAdapter.setList(object);
        invokeRecyclerViewAnimation();
    }

    private void invokeRecyclerViewAnimation() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        fadeInAnimation.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
        recyclerView.setLayoutAnimation(new LayoutAnimationController(fadeInAnimation));
        recyclerView.startLayoutAnimation();
    }

    @Override
    public void failure(RetrofitError error) {
        errorHandler.handleError(error);
    }

    @Override
    public void run() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public final void onItemClicked(T item) {
        startActivity(fragmentsProvider.provideFragmentIntent(item));
    }

    @Override
    public void onDestroy() {
        getNetworkRequester().cancelRequest();
        super.onDestroy();
    }
}

package com.morcinek.showcase.education;

import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.dagger.components.ShowcaseFragment;
import com.morcinek.showcase.general.controllers.RefreshProgressController;
import com.morcinek.showcase.general.handlers.RetryLayoutErrorHandler;
import com.morcinek.showcase.network.NetworkFacade;
import com.morcinek.showcase.network.model.Education;
import com.morcinek.showcase.network.response.NetworkResponseListener;

import java.util.List;

import javax.inject.Inject;

import retrofit.RetrofitError;

public class EducationListFragment extends ShowcaseFragment implements NetworkResponseListener<List<Education>>, SwipeRefreshLayout.OnRefreshListener, AbstractRecyclerViewAdapter.OnItemClickListener<Education>, View.OnClickListener {

    @Inject
    NetworkFacade networkFacade;

    @Inject
    RetryLayoutErrorHandler errorHandler;

    @Inject
    RefreshProgressController progressController;

    private EducationListAdapter listAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.education_list;
    }

    @Override
    public String getTitle() {
        return getString(R.string.education_list_title);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.retry_layout).setOnClickListener(this);

        setupRecyclerView(view);
        setupSwipeRefreshLayout(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    private void setupSwipeRefreshLayout(View view) {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accentColor));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        listAdapter = new EducationListAdapter(getActivity());
        listAdapter.setItemClickListener(this);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void success(List<Education> object) {
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
    public void onItemClicked(Education item) {
        //TODO
    }

    @Override
    public void onRefresh() {
        networkFacade.getEducation(this, progressController);
    }

    @Override
    public void onClick(View v) {
        errorHandler.hideErrorMessage();
        onRefresh();
    }
}

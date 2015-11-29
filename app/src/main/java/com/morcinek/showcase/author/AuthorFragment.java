package com.morcinek.showcase.author;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.author.model.Author;
import com.morcinek.showcase.general.controllers.ContentProgressController;
import com.morcinek.showcase.general.controllers.RefreshProgressController;
import com.morcinek.showcase.general.handlers.RetryErrorHandler;
import com.morcinek.showcase.general.network.requesters.AuthorRequester;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.home.navigation.ToolbarHostFragment;

import javax.inject.Inject;

import retrofit.RetrofitError;

public class AuthorFragment extends ToolbarHostFragment implements NetworkResponseListener<Author>, Runnable, OnRefreshListener {

    @Inject
    AuthorRequester authorRequester;

    @Inject
    RetryErrorHandler errorHandler;

    @Inject
    protected RefreshProgressController progressController;

    @Inject
    protected ContentProgressController progressBarController;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.author;
    }

    @Override
    public String getTitle() {
        return getString(R.string.author_title);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authorRequester.initialize(this, progressController, progressBarController);

        onRefresh();

        setupSwipeRefreshLayout(view);

        errorHandler.registerAction(this);
        errorHandler.registerViewGroup((android.view.ViewGroup) view);
    }

    private void setupSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accent));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onRefresh() {
        authorRequester.requestAuthor();
    }

    @Override
    public void success(Author object) {
        setupTextViewWithText(R.id.name, object.getName());
        setupTextViewWithText(R.id.description, object.getDescription());
    }

    private void setupTextViewWithText(int resourceName, String value) {
        if (getView() != null) {
            ((TextView) getView().findViewById(resourceName)).setText(value);
        }
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
    public void onDestroy() {
        authorRequester.cancelRequest();
        super.onDestroy();
    }
}
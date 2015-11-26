package com.morcinek.showcase.author;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.author.model.Author;
import com.morcinek.showcase.general.controllers.ProgressBarController;
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
    protected ProgressBarController progressBarController;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.author, menu);
        super.onCreateOptionsMenu(menu, inflater);
//        progressBarController.setMenuItem(menu.findItem(R.id.action_refresh));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
//        progressBarController.setMenuItem(menu.findItem(R.id.action_refresh));
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

    public void onRefresh() {
        authorRequester.requestAuthor();
    }

    @Override
    public void success(Author object) {
        setupTextViewWithText(R.id.name, object.getName());
        setupTextViewWithText(R.id.description, object.getDescription());
        setupTextViewWithText(R.id.email, object.getEmail());
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
package com.morcinek.showcase.author;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.author.model.Author;
import com.morcinek.showcase.general.controllers.ProgressBarController;
import com.morcinek.showcase.general.handlers.RetryErrorHandler;
import com.morcinek.showcase.general.network.requesters.AuthorRequester;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;
import com.morcinek.showcase.home.navigation.ToolbarHostFragment;

import javax.inject.Inject;

import retrofit.RetrofitError;

public class AuthorFragment extends ToolbarHostFragment implements NetworkResponseListener<Author>, Runnable {

    @Inject
    AuthorRequester authorRequester;

    @Inject
    RetryErrorHandler errorHandler;

    @Inject
    ProgressBarController progressBarController;

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
        authorRequester.initialize(this, progressBarController);
        authorRequester.requestAuthor();

        errorHandler.registerAction(this);
        errorHandler.registerViewGroup((android.view.ViewGroup) view);
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
        authorRequester.requestAuthor();
    }

    @Override
    public void onDestroy() {
        authorRequester.cancelRequest();
        super.onDestroy();
    }
}
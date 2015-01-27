package com.morcinek.showcase.author;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.dagger.components.ShowcaseFragment;
import com.morcinek.showcase.general.controllers.ProgressBarController;
import com.morcinek.showcase.general.handlers.RetryLayoutErrorHandler;
import com.morcinek.showcase.general.network.NetworkFacade;
import com.morcinek.showcase.general.network.model.Author;
import com.morcinek.showcase.general.network.response.NetworkResponseListener;

import javax.inject.Inject;

import retrofit.RetrofitError;

public class AuthorFragment extends ShowcaseFragment implements NetworkResponseListener<Author>, View.OnClickListener {

    @Inject
    NetworkFacade networkFacade;

    @Inject
    RetryLayoutErrorHandler errorHandler;

    @Inject
    ProgressBarController progressBarController;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.author;
    }

    @Override
    public Integer getColor() {
        return R.color.accentColor;
    }

    @Override
    public String getTitle() {
        return getString(R.string.author_title);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.retry_layout).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        networkFacade.getAuthor(this, progressBarController);
    }

    @Override
    public void success(Author object) {
        errorHandler.hideErrorMessage();
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
    public void onClick(View v) {
        errorHandler.hideErrorMessage();
        networkFacade.getAuthor(this, progressBarController);
    }
}
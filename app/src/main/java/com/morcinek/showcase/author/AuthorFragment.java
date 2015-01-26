package com.morcinek.showcase.author;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.navigation.ShowcaseFragment;
import com.morcinek.showcase.general.network.ProgressBarController;
import com.morcinek.showcase.general.network.RetryLayoutErrorHandler;
import com.morcinek.showcase.network.NetworkFacade;
import com.morcinek.showcase.network.model.Author;
import com.morcinek.showcase.network.response.NetworkResponseListener;

import javax.inject.Inject;

import retrofit.RetrofitError;

public class AuthorFragment extends ShowcaseFragment implements NetworkResponseListener<Author>, View.OnClickListener {

    @Inject
    NetworkFacade networkFacade;

    @Inject
    RetryLayoutErrorHandler errorHandler;

    @Inject
    ProgressBarController progressBarController;

    private TextView name;
    private TextView description;
    private TextView email;

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

        name = (TextView) view.findViewById(R.id.name);
        description = (TextView) view.findViewById(R.id.description);
        email = (TextView) view.findViewById(R.id.email);
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
        name.setText(object.getName());
        description.setText(object.getDescription());
        email.setText(object.getEmail());
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
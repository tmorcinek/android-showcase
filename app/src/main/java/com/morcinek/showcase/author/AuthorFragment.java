package com.morcinek.showcase.author;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.navigation.ShowcaseFragment;
import com.morcinek.showcase.network.NetworkFacade;
import com.morcinek.showcase.network.error.ErrorHandler;
import com.morcinek.showcase.network.model.Author;
import com.morcinek.showcase.network.response.NetworkResponseListener;
import com.morcinek.showcase.network.response.ProgressController;

import javax.inject.Inject;

import retrofit.RetrofitError;

public class AuthorFragment extends ShowcaseFragment implements NetworkResponseListener<Author> {

    @Inject
    NetworkFacade networkFacade;

    @Inject
    ErrorHandler errorHandler;

    private TextView name;
    private TextView description;
    private TextView email;
    private LinearLayout telephonesLayout;
    private RecyclerView recyclerView;

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
//        telephonesLayout = (LinearLayout) view.findViewById(R.id.telephones_layout);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        networkFacade.getAuthor(this);
    }

    @Override
    public void success(Author object) {
        name.setText(object.getName());
        description.setText(object.getDescription());
        email.setText(object.getEmail());
    }

    @Override
    public void failure(RetrofitError error) {
        errorHandler.handleError(error);
    }
}
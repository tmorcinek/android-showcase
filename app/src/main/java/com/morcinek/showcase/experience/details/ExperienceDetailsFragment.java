package com.morcinek.showcase.experience.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.details.AbstractDetailsFragment;
import com.morcinek.showcase.experience.model.Experience;

public class ExperienceDetailsFragment extends AbstractDetailsFragment<Experience> implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.experience_details;
    }

    @Override
    protected String getTitle() {
        return getData().getPosition();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTextViews(view, getData());
        setupLinkButton(view);
    }

    private void setupLinkButton(View view) {
        view.findViewById(R.id.link_button).setOnClickListener(this);
    }

    private void setupTextViews(View view, Experience experience) {
        setupTextViewWithText(view, R.id.company, experience.getCompanyName());
        setupTextViewWithText(view, R.id.position, experience.getPosition());
        setupTextViewWithText(view, R.id.dates, experience.getDates());
        setupTextViewWithText(view, R.id.description, experience.getDescription());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.link_button:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getData().getLink().getUrl())));
                break;
        }
    }
}

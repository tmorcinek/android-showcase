package com.morcinek.showcase.education.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.morcinek.showcase.R;
import com.morcinek.showcase.details.AbstractDetailsFragment;
import com.morcinek.showcase.education.model.Education;

public class EducationDetailsFragment extends AbstractDetailsFragment<Education> implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.education_details;
    }

    @Override
    protected String getTitle() {
        return getData().getUniversity();
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

    private void setupTextViews(View view, Education education) {
        setupTextViewWithText(view, R.id.title, education.getUniversity());
        setupTextViewWithText(view, R.id.speciality, education.getSpeciality());
        setupTextViewWithText(view, R.id.description, education.getDescription());
        setupTextViewWithText(view, R.id.dates, education.getDates());
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

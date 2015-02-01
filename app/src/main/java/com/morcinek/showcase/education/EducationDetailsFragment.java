package com.morcinek.showcase.education;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.home.navigation.ToolbarHostFragment;
import com.morcinek.showcase.general.network.model.Education;

public class EducationDetailsFragment extends ToolbarHostFragment implements View.OnClickListener {

    private Education education;

    private LinearLayout detailsContainer;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.education_details;
    }

    @Override
    public Integer getColor() {
        return R.color.accentColor;
    }

    @Override
    public String getTitle() {
        return getString(R.string.education_details_title);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            education = arguments.getParcelable(Education.class.getName());
        }

        setupTextViews(view);
        setupLinkButton(view);

        detailsContainer = (LinearLayout) view.findViewById(R.id.details_container);
        for (int i = 1; i < detailsContainer.getChildCount(); i++) {
            View rowView = detailsContainer.getChildAt(i);
            rowView.animate().setStartDelay(200 + i * 30).scaleX(1).scaleY(1);
        }
    }

    private void setupLinkButton(View view) {
        Button linkButton = (Button) view.findViewById(R.id.link_button);
        linkButton.setText(education.getLink().getName());
        linkButton.setOnClickListener(this);
    }

    private void setupTextViews(View view) {
        setupTextViewWithText(view, R.id.title, education.getUniversity());
        setupTextViewWithText(view, R.id.speciality, education.getSpeciality());
        setupTextViewWithText(view, R.id.description, education.getDescription());
        setupTextViewWithText(view, R.id.dates, education.getDates());
    }

    private void setupTextViewWithText(View view, int resourceId, String text) {
        ((TextView) view.findViewById(resourceId)).setText(text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.link_button:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(education.getLink().getUrl())));
                break;
        }
    }
}

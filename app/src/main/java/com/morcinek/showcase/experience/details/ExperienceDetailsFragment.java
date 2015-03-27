package com.morcinek.showcase.experience.details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.experience.model.Experience;
import com.morcinek.showcase.home.navigation.ToolbarHostFragment;

public class ExperienceDetailsFragment extends ToolbarHostFragment implements View.OnClickListener {

    private Experience experience;

    private LinearLayout detailsContainer;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.experience_details;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @SuppressLint("ValidFragment")
    private ExperienceDetailsFragment() {
        super();
    }

    public static ExperienceDetailsFragment newInstance(Experience experience) {
        ExperienceDetailsFragment fragment = new ExperienceDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(Experience.class.getName(), experience);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            experience = arguments.getParcelable(Experience.class.getName());
        }

        setupTextViews(view);
        setupLinkButton(view);
    }

    private void setupLinkButton(View view) {
        view.findViewById(R.id.link_button).setOnClickListener(this);
    }

    private void setupTextViews(View view) {
        setupTextViewWithText(view, R.id.company, experience.getCompanyName());
        setupTextViewWithText(view, R.id.position, experience.getPosition());
        setupTextViewWithText(view, R.id.dates, experience.getDates());
        setupTextViewWithText(view, R.id.description, experience.getDescription());
    }

    private void setupTextViewWithText(View view, int resourceId, String text) {
        ((TextView) view.findViewById(resourceId)).setText(text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.link_button:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(experience.getLink().getUrl())));
                break;
        }
    }


}

package com.morcinek.showcase.skills.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.morcinek.showcase.R;
import com.morcinek.showcase.details.AbstractDetailsFragment;
import com.morcinek.showcase.general.network.model.Link;
import com.morcinek.showcase.skills.model.Skill;

public class SkillDetailsFragment extends AbstractDetailsFragment<Skill> implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.skill_details;
    }

    @Override
    protected String getTitle() {
        return getData().getTitle();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTextViews(view, getData());
        setupLinkButton(view.findViewById(R.id.link_button), getData().getLink());
    }

    private void setupLinkButton(View view, Link link) {
        if (link != null) {
            view.setOnClickListener(this);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void setupTextViews(View view, Skill experience) {
        setupTextViewWithText(view, R.id.title, experience.getTitle());
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

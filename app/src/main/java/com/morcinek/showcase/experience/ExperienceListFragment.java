package com.morcinek.showcase.experience;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.details.DetailsActivity;
import com.morcinek.showcase.education.details.EducationDetailsFragment;
import com.morcinek.showcase.experience.details.ExperienceDetailsFragment;
import com.morcinek.showcase.general.AbstractListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.experience.model.Experience;
import com.morcinek.showcase.general.network.requesters.ExperienceRequester;

import javax.inject.Inject;

import lombok.Getter;

public class ExperienceListFragment extends AbstractListFragment<Experience> {

    @Inject
    @Getter
    ExperienceRequester networkRequester;

    @Override
    protected AbstractRecyclerViewAdapter<Experience, ? extends RecyclerView.ViewHolder> getCreateListAdapter() {
        return new ExperienceListAdapter(getActivity());
    }

    @Override
    public String getTitle() {
        return getString(R.string.experience_list_title);
    }

    @Override
    public void onItemClicked(Experience item) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(Class.class.getName(), ExperienceDetailsFragment.class.getName());
        intent.putExtra(Object.class.getName(), item);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        networkRequester.requestExperience();
    }
}

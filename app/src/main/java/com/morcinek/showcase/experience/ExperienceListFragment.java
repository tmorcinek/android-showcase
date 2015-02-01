package com.morcinek.showcase.experience;

import android.support.v7.widget.RecyclerView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.education.EducationListAdapter;
import com.morcinek.showcase.general.AbstractShowcaseListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.network.model.Education;
import com.morcinek.showcase.general.network.model.Experience;

public class ExperienceListFragment extends AbstractShowcaseListFragment<Experience> {

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
        //TODO
    }

    @Override
    public void onRefresh() {
        networkFacade.getExperience(this, progressController);
    }
}

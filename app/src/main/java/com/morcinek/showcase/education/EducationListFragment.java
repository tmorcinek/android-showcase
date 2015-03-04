package com.morcinek.showcase.education;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.AbstractShowcaseListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.general.network.requesters.EducationsRequester;

import javax.inject.Inject;

public class EducationListFragment extends AbstractShowcaseListFragment<Education> {

    @Inject
    EducationsRequester educationsRequester;

    @Override
    protected AbstractRecyclerViewAdapter<Education, ? extends RecyclerView.ViewHolder> getCreateListAdapter() {
        return new EducationListAdapter(getActivity());
    }

    @Override
    public String getTitle() {
        return getString(R.string.education_list_title);
    }

    @Override
    public void onItemClicked(Education item) {
        //TODO
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        educationsRequester.initialize(this, progressController);
    }

    @Override
    public void onRefresh() {
        educationsRequester.requestEducations();
    }
}

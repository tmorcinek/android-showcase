package com.morcinek.showcase.education;

import android.support.v7.widget.RecyclerView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.general.AbstractShowcaseListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.network.requesters.EducationsRequester;

import javax.inject.Inject;

import lombok.Getter;

public class EducationListFragment extends AbstractShowcaseListFragment<Education> {

    @Getter
    @Inject
    EducationsRequester networkRequester;

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
    public void onRefresh() {
        networkRequester.requestEducations();
    }
}

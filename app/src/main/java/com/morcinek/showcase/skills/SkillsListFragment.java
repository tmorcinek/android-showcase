package com.morcinek.showcase.skills;

import android.support.v7.widget.RecyclerView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.AbstractShowcaseListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.skills.model.Skill;

public class SkillsListFragment extends AbstractShowcaseListFragment<Skill> {

    @Override
    protected AbstractRecyclerViewAdapter<Skill, ? extends RecyclerView.ViewHolder> getCreateListAdapter() {
        return new SkillsListAdapter(getActivity());
    }

    @Override
    public String getTitle() {
        return getString(R.string.skills_list_title);
    }

    @Override
    public void onItemClicked(Skill item) {
        //TODO
    }

    @Override
    public void onRefresh() {
        networkFacade.getSkills(this, progressController);
    }
}

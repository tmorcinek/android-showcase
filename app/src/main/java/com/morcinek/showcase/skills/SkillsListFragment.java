package com.morcinek.showcase.skills;

import android.support.v7.widget.RecyclerView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.general.AbstractListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.network.requesters.SkillsRequester;
import com.morcinek.showcase.skills.model.Skill;

import javax.inject.Inject;

import lombok.Getter;

public class SkillsListFragment extends AbstractListFragment<Skill> {

    @Getter
    @Inject
    SkillsRequester networkRequester;


    @Override
    protected AbstractRecyclerViewAdapter<Skill, ? extends RecyclerView.ViewHolder> getCreateListAdapter() {
        return new SkillsListAdapter(getActivity());
    }

    @Override
    public String getTitle() {
        return getString(R.string.skills_list_title);
    }

    @Override
    public void onRefresh() {
        networkRequester.requestSkills();
    }
}

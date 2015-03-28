package com.morcinek.showcase.details.provider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.google.common.collect.Maps;
import com.morcinek.showcase.details.DetailsActivity;
import com.morcinek.showcase.education.details.EducationDetailsFragment;
import com.morcinek.showcase.education.model.Education;
import com.morcinek.showcase.experience.details.ExperienceDetailsFragment;
import com.morcinek.showcase.experience.model.Experience;
import com.morcinek.showcase.skills.details.SkillDetailsFragment;
import com.morcinek.showcase.skills.model.Skill;

import java.util.Map;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class DetailsFragmentProvider implements FragmentsProvider {

    private final Map<String, Class<? extends Fragment>> detailsFragments = Maps.newHashMapWithExpectedSize(4);

    private final Context context;

    public DetailsFragmentProvider(Context context) {
        this.context = context;
        registerFragment(Education.class, EducationDetailsFragment.class);
        registerFragment(Experience.class, ExperienceDetailsFragment.class);
        registerFragment(Skill.class, SkillDetailsFragment.class);
    }

    private void registerFragment(Class object, Class<? extends Fragment> fragmentClass) {
        detailsFragments.put(object.getName(), fragmentClass);
    }

    @Override
    public Fragment provideFragment(Bundle bundle) {
        try {
            Fragment fragment = detailsFragments.get(bundle.getString(Class.class.getName())).newInstance();
            fragment.setArguments(bundle);
            return fragment;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Intent provideFragmentIntent(Parcelable object) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(Class.class.getName(), object.getClass().getName());
        intent.putExtra(Object.class.getName(), object);
        return intent;
    }
}

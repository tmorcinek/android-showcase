package com.morcinek.showcase.details.provider;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.common.collect.Maps;
import com.morcinek.showcase.education.details.EducationDetailsFragment;
import com.morcinek.showcase.experience.details.ExperienceDetailsFragment;

import java.util.Map;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class DetailsFragmentProvider implements FragmentsProvider {

    private Map<String, Class<? extends Fragment>> detailsFragments = Maps.newHashMapWithExpectedSize(4);

    public DetailsFragmentProvider() {
        registerFragment(EducationDetailsFragment.class);
        registerFragment(ExperienceDetailsFragment.class);
    }

    private void registerFragment(Class<? extends Fragment> fragmentClass) {
        detailsFragments.put(fragmentClass.getName(), fragmentClass);
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
}

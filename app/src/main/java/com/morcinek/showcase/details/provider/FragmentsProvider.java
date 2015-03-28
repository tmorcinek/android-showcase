package com.morcinek.showcase.details.provider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public interface FragmentsProvider {

    Fragment provideFragment(Bundle bundle);

    Intent provideFragmentIntent(Parcelable object);
}

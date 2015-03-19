package com.morcinek.showcase.splash;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.Assertions.assertThat;


/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, manifest = "/src/test/AndroidManifest.xml", resourceDir = "../main/res")
public class SplashActivityTest {

    private SplashActivity splashActivity;

    @Before
    public void setup() {
        splashActivity = Robolectric.setupActivity(SplashActivity.class);
    }

    @Test
    public void activityCreated() throws Exception {
        assertThat(splashActivity).isNotNull();
    }
}

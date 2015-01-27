package com.morcinek.showcase.general.helpers;

import android.animation.ArgbEvaluator;
import android.support.v7.widget.Toolbar;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class UIHelper {

    public static void updateViewColor(Toolbar toolbar, int firstColor, int secondColor, float percentage) {
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        toolbar.setBackgroundColor((Integer) argbEvaluator.evaluate(percentage, firstColor, secondColor));
    }
}

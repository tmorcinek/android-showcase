package com.morcinek.showcase.general.helpers;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.morcinek.showcase.R;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class UIHelper {

    public static void setupStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //TODO behaviour for Lollipop
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View view = activity.findViewById(R.id.status_bar);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = getStatusBarHeight(activity);
            view.setLayoutParams(layoutParams);
        }
    }

    private static int getStatusBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return activity.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static void updateViewColor(Toolbar toolbar, int firstColor, int secondColor, float percentage) {
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        toolbar.setBackgroundColor((Integer) argbEvaluator.evaluate(percentage, firstColor, secondColor));
    }
}

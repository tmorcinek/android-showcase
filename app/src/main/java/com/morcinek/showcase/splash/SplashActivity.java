package com.morcinek.showcase.splash;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.morcinek.showcase.R;


public class SplashActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        findViewById(R.id.application_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.application_button:
                finish();
                break;
        }
    }
}

package com.morcinek.showcase.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.morcinek.showcase.R;
import com.morcinek.showcase.home.HomeActivity;


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
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                break;
        }
    }
}

package com.laowch.imagepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by nara on 6/3/2015.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if(SharedPreferenceManager.getStringPreferences("Pin",SplashActivity.this,"0")!="0") {
                    startActivity(new Intent(SplashActivity.this, AppLogin.class));
                }
                else
                {
                    startActivity(new Intent(SplashActivity.this, PinChooseActivity.class));
                }
                SplashActivity.this.finish();

            }
        }, 1000);
    }

}
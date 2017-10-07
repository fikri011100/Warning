package com.faishalbadri.hijab.First.Splash;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.faishalbadri.hijab.Helper.SessionManager;
import com.faishalbadri.hijab.R;

public class SplashActivity extends AppCompatActivity {

    SessionManager sesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sesi = new SessionManager(SplashActivity.this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // pengecekan sesi
                sesi.checkLogin();
                finish();
            }
        }, 2000 ); // satuan dalam milisecond

    }
}

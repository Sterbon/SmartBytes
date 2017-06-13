package com.sterbon.smartbytes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Utsav on 6/5/2017.
 */

public class Splash extends Activity {
    protected void onCreate(Bundle savedInstantstate) {
        super.onCreate(savedInstantstate);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
}
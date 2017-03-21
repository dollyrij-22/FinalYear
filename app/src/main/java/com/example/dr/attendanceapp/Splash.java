package com.example.dr.attendanceapp;

/**
 * Created by DR on 3/20/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DR on 1/2/2017.
 */
public class Splash extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread splashThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                    Intent startMainScreen = new Intent(getApplicationContext(),teachers_login.class);
                    startActivity(startMainScreen);
                    finish();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        splashThread.start();
    }
}

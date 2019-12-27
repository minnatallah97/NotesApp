package com.example.notesapp.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.notesapp.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 300000000; //This is 3 seconds
    Button Sign;
    TextView SignUP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Sign = findViewById(R.id.signin_btn);
        SignUP  = findViewById(R.id.signup_tv);

        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(SplashActivity.this, SigninActivity.class);
                                          startActivity(intent);

                                          finish();
                                      }
                                  },
                SPLASH_TIME);

    }
}
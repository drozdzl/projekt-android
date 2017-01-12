package com.example.lukasz.aplikacja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button registerButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        registerButton = (Button) findViewById(R.id.registerActivityButton);
        loginButton = (Button) findViewById(R.id.loginActivityButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButtonClicked();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonOnClicked();
            }
        });
    }

    private void registerButtonClicked() {

                Intent moveToRegisterActivity = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(moveToRegisterActivity);
            }
    private void loginButtonOnClicked()
    {
        Intent moveToLoginActivity = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(moveToLoginActivity);
    }

    }

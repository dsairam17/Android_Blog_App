package com.example.dsair.techfortechie;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmailEt, mPasswordEt;
    String mLoginEmail, mLoginPass;
    TextInputLayout mInputLayoutEmail, mInputLayoutUsername, mInputLayoutPassword;
    Button mRegister;
    FloatingActionButton mBackToLogin;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmailEt = (EditText) findViewById(R.id.register_email);
        mPasswordEt = (EditText) findViewById(R.id.register_password);
        mBackToLogin = (FloatingActionButton) findViewById(R.id.fab_back_to_login);
        mBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);*/
                finish();
            }
        });
    }
}

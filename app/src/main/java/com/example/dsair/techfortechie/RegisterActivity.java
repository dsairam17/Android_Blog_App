package com.example.dsair.techfortechie;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmailEt, mUsernameEt, mPasswordEt;
    String mRegisterEmail, mRegisterUsername, mRegisterPass;
    TextInputLayout mInputLayoutEmail, mInputLayoutUsername, mInputLayoutPassword;
    Button mRegister;
    FloatingActionButton mBackToLogin;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmailEt = (EditText) findViewById(R.id.register_email);
        mPasswordEt = (EditText) findViewById(R.id.register_password);
        mUsernameEt = (EditText) findViewById(R.id.register_username);
        mRegister = (Button) findViewById(R.id.login_btn);
        mInputLayoutEmail = (TextInputLayout) findViewById(R.id.register_email_input_layout);
        mInputLayoutUsername = (TextInputLayout) findViewById(R.id.register_username_input_layout);
        mInputLayoutPassword = (TextInputLayout) findViewById(R.id.register_password_input_layout);
        mBackToLogin = (FloatingActionButton) findViewById(R.id.fab_back_to_login);

        mBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);*/
                finish();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });

        mEmailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Validation.isValidEmail(s)){
                    mInputLayoutEmail.setError("Enter a valid Email Address");
                }
                else
                    mInputLayoutEmail.setErrorEnabled(false);
            }
        });

    }

    private void userRegister() {
        mRegisterEmail = mEmailEt.getText().toString();
        mRegisterUsername = mUsernameEt.getText().toString();
        mRegisterPass = mPasswordEt.getText().toString();
        if(!Validation.isValidEmail(mRegisterEmail)){
            mInputLayoutEmail.setError("Enter a valid Email Address");
        }
        else if(mRegisterUsername.isEmpty()){
            mInputLayoutUsername.setError("Enter an username");
        }
        else if(mRegisterPass.isEmpty()){
            mInputLayoutPassword.setError("Enter password");
        }
        else {
            String action = "register";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(action, mRegisterEmail, mRegisterUsername, mRegisterPass);
        }
    }
}
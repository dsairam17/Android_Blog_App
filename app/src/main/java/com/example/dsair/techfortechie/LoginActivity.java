package com.example.dsair.techfortechie;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity{

    EditText mEmailEt, mPasswordEt;
    String mLoginEmail, mLoginPass;
    FloatingActionButton mRegister;
    TextInputLayout mInputLayoutEmail, mInputLayoutPassword;
    Button mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailEt = (EditText) findViewById(R.id.email);
        mPasswordEt = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login_btn);
        mRegister = (FloatingActionButton) findViewById(R.id.fab);
        mInputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        mInputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this ,RegisterActivity.class);
                startActivity(intent);
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


    public void userLogin(){
        mLoginEmail = mEmailEt.getText().toString();
        mLoginPass = mPasswordEt.getText().toString();
        if(!Validation.isValidEmail(mLoginEmail)){
            mInputLayoutEmail.setError("Enter a valid Email Address");
        }
        else if(mLoginPass.isEmpty()){
            mInputLayoutPassword.setError("Enter password");
        }
        else {
            String action = "login";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(action, mLoginEmail, mLoginPass);
//            finish(); Change it
        }
    }
}
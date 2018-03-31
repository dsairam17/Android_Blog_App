package com.example.dsair.techfortechie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText mEmailEt, mPasswordEt;
    String mLoginName, mLoginPass;
    Button mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailEt = (EditText) findViewById(R.id.email);
        mPasswordEt = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login_btn);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    public void userLogin(){
        mLoginName = mEmailEt.getText().toString();
        mLoginPass = mPasswordEt.getText().toString();
        String action = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(action, mLoginName, mLoginPass);
    }
}
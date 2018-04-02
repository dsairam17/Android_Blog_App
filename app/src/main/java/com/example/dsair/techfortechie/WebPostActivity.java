package com.example.dsair.techfortechie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_post);
        String permalink = getIntent().getExtras().getString("permalink");
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://techfortechie.com/posts/" + permalink);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}

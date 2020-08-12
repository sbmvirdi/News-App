package com.shubamvirdi.newsapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.shubamvirdi.newsapp.R;

public class DetailedNews extends AppCompatActivity {

    private WebView newsWebView;
    private String detailedNewsUrl;
    private Toolbar toolbar;
    private ProgressBar progress;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        // initialize ids
        toolbar = findViewById(R.id.toolbar);
        newsWebView = findViewById(R.id.newsWebView);
        progress = findViewById(R.id.progress);

        // setup toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // get the url
        detailedNewsUrl = getIntent().getExtras().getString("url");
        assert detailedNewsUrl != null;

        // load the url in webview
        newsWebView.loadUrl(detailedNewsUrl);
        newsWebView.getSettings().setJavaScriptEnabled(true);

        // web page load listener
        newsWebView.setWebViewClient(new WebViewClient() {
            // called when page has finished loading
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // if back button is pressed in home
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
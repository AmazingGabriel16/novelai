package com.example.novelai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView myWebView;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Status bar removal
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // Below is deprecated
        // Try https://developer.android.com/training/system-ui/immersive#java
        // See also https://developer.android.com/reference/android/view/WindowInsetsController
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE

        // Main webview content
        setContentView(R.layout.activity_main);
        myWebView = (WebView) findViewById(R.id.webview);

        // Setting up a new webview client to help in fixing SSL issue

        WebSettings wS = myWebView.getSettings();
        wS.setDomStorageEnabled(true);
        wS.setJavaScriptEnabled(true);

        // Setting up a new webview client to help in fixing SSL issue
        myWebView.setWebViewClient(new customWebViewClient());

        // Loading target URL
        myWebView.loadUrl("https://www.novelai.net");
    }

    public static class customWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }

    @Override
    public void onBackPressed(){
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}
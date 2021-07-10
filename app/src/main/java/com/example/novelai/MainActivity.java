package com.example.novelai;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Status bar removal
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getActionBar().setDisplayHomeAsUpEnabled(false);

        // Main webview content
        setContentView(R.layout.activity_main);
        mywebView=(WebView) findViewById(R.id.webview);
        mywebView.setWebViewClient(new WebViewClient());
        mywebView.getSettings().setDomStorageEnabled(true);
        mywebView.loadUrl("https://www.novelai.net");
        WebSettings webSettings=mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    public class mywebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed(){
        if(mywebView.canGoBack()) {
            mywebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}
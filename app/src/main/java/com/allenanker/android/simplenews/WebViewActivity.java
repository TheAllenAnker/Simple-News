package com.allenanker.android.simplenews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

public class WebViewActivity extends AppCompatActivity {
    private WebView mNewsWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent caller = getIntent();
        mNewsWebView = findViewById(R.id.news_webview);
        mProgressBar = findViewById(R.id.webview_progressbar);

        Map<String, String> args = new HashMap<>();
        args.put("User-Agent", "Android");
        mNewsWebView.loadUrl(caller.getStringExtra("url"), args);
        mNewsWebView.setWebViewClient(mWebViewClient);
        WebSettings webSettings = mNewsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

//        @Override
//        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            Log.e("ssl error", error.toString());
//            if (error.getPrimaryError() == SslError.SSL_INVALID) {
//                handler.proceed();
//            } else {
//                handler.cancel();
//            }
//        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mNewsWebView.destroy();
        mNewsWebView = null;
    }
}

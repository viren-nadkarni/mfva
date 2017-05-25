package com.appknox.mfva;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by viren on 23/5/17.
 */

public class WebViewActivity extends Activity {
    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://example.com");
    }
}

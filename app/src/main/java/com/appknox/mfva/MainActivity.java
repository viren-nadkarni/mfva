package com.appknox.mfva;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonApi, buttonWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        buttonApi = (Button) findViewById(R.id.buttonApi);
        buttonApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("API", "Requesting");
            }
        });

        buttonWebView = (Button) findViewById(R.id.buttonWebView);
        buttonWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("WebView", "Opening");
                Intent intent = new Intent(context, WebViewActivity.class);
		        startActivity(intent);
            }
        });
    }
}

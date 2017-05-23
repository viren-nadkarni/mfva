package com.appknox.mfva;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;
import okhttp3.Call;


public class MainActivity extends AppCompatActivity {
    private Button buttonApi, buttonWebView, buttonSharedPref, buttonDebugLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        buttonApi = (Button) findViewById(R.id.buttonApi);
        buttonApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                OkHttpClient client = new OkHttpClient();
                try {
                    Request request = new Request.Builder().url("http://vapi.appknox.io/").build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            if (!response.isSuccessful()) {
                                throw new IOException("Response: " + response);
                            }
                            Snackbar.make(v, "Success", Snackbar.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    Snackbar.make(v, e.toString(), Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        buttonWebView = (Button) findViewById(R.id.buttonWebView);
        buttonWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
		        startActivity(intent);
            }
        });

        buttonSharedPref = (Button) findViewById(R.id.buttonSharedPref);
        buttonSharedPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0);
                SharedPreferences.Editor editor = pref.edit();

                String[] keys = new String[] {
                        "java.class.version",
                        "java.home",
                        "java.library.path",
                        "java.vendor",
                        "java.vendor.url",
                        "java.specification.name",
                        "java.vm.name",
                        "java.vm.version",
                        "java.vm.specification.name",
                        "java.vm.specification.version",
                        "os.arch",
                        "os.name",
                        "os.version",
                };
                String key = keys[(int) (Math.random() * keys.length)];
                editor.putString(key, System.getProperty(key));
                editor.commit();

                Snackbar.make(v, key + ": " + pref.getString(key, null), Snackbar.LENGTH_SHORT).show();
            }
        });

        buttonDebugLog = (Button) findViewById(R.id.buttonDebugLog);
        buttonDebugLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] quotes = new String[] {
                        "Any given program, when running correctly, is obsolete.",
                        "Disco is to music what Etch-A-Sketch is to art.",
                        "Computer Science is merely the post-Turing decline in formal systems theory.",
                        "Sometimes the only solution is to find a new problem.",
                        "There are 10 kinds of people. Those who know binary and those who don't.",
                        "Whom computers would destroy, they must first drive mad.",
                        "I program, therefore I am.",
                        "If Java had true garbage collection, most programs would delete themselves upon execution.",
                        "Hardware, n.: The parts of a computer system that can be kicked.",
                        "There are two ways to write error-free programs. Only the third one works.",
                        "Why isn't \"palindrome\" spelled the same way backwards?",
                        "A language that doesn't affect the way you think about programming is not worth knowing.",
                        "All programmers are playwrights and all computers are lousy actors.",
                        "Every program has two purposes ― one for which it was written and another for which it wasn't.",
                        "Every program is a part of some other program, and rarely fits.",
                };
                String quote = quotes[(int) (Math.random() * quotes.length)];
                Log.d("YOLO", quote);
                Snackbar.make(v, quote, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}

package com.appknox.mfva;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_requests);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_api_requests);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView textViewLogs = (TextView) findViewById(R.id.textview_api_requests);
        final FloatingActionButton buttonStartApiRequests = (FloatingActionButton) findViewById(R.id.button_start_api_requests);

        buttonStartApiRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new Thread(new Runnable() {
                    public void run() {
                        final OkHttpClient client = new OkHttpClient();
                        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                        // Connection check
                        Request request0 = new Request.Builder()
                                .url("http://vapi.appknox.io")
                                .build();
                        try {
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("Connecting to server...\n");
                                }
                            });
                            client.newCall(request0).execute();
                        } catch (IOException e) {
                            Snackbar.make(v, e.toString(), Snackbar.LENGTH_SHORT).show();
                        }

                        // Authenticate
                        RequestBody body = RequestBody.create(JSON, "{\"auth\":{\"passwordCredentials\":{\"username\":\"user1\",\"password\":\"pass1\"}}}");
                        Request request1 = new Request.Builder()
                                .url("http://vapi.appknox.io/tokens")
                                .post(body)
                                .build();
                        try {
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("Authenticating...\n");
                                }
                            });
                            Response response = client.newCall(request1).execute();
                            final String body0 = response.body().string();
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append(body0);
                                }
                            });
                        } catch (IOException e) {
                            Snackbar.make(v, e.toString(), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }).start();
            }
        });
    }
}

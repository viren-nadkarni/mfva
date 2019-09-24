package com.appknox.mfva;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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

                        String token = null;

                        // Connection check
                        Request request0 = new Request.Builder()
                                .url("http://vapi.appknox.io")
                                .build();
                        try {
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("  hostname:vapi.appknox.io\n");
                                }
                            });
                            client.newCall(request0).execute();
                        } catch (IOException e) {
                            Snackbar.make(v, e.toString(), Snackbar.LENGTH_SHORT).show();
                        }

                        // Authenticate
                        RequestBody body1 = RequestBody.create(JSON, "{\"auth\":{\"passwordCredentials\":{\"username\":\"user1\",\"password\":\"pass1\"}}}");
                        Request request1 = new Request.Builder()
                                .url("http://vapi.appknox.io/tokens")
                                .post(body1)
                                .build();
                        try {
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("Authenticating...\n");
                                }
                            });
                            String response = client.newCall(request1).execute().body().string();
                            JSONObject json1 = new JSONObject(response);
                            token = json1.getJSONObject("access").getJSONObject("token").getString("id");

                            final String token0 = token,
                                    expiry0 = json1.getJSONObject("access").getJSONObject("token").getString("expires");

                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("  token:" + token0 + "\n");
                                    textViewLogs.append("  expiry:" + expiry0 + "\n");
                                }
                            });
                        } catch (IOException|JSONException e) {
                            Snackbar.make(v, e.toString(), Snackbar.LENGTH_SHORT).show();
                        }

                        if(token == null) {
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("Unable to authenticate!\n");
                                }
                            });
                            return;
                        }

                        // Get user
                        Request request2 = new Request.Builder()
                                .header("X-Auth-Token", token)
                                .url("http://vapi.appknox.io/user/1")
                                .build();
                        try {
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("Fetching user ID...\n");
                                }
                            });
                            JSONObject json2 = new JSONObject(client.newCall(request2).execute().body().string());
                            final String id2 = json2.getJSONObject("response").getJSONObject("user").getString("id");
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("  userid:" + id2 + "\n");
                                }
                            });
                        } catch (IOException|JSONException e) {
                            Snackbar.make(v, e.toString(), Snackbar.LENGTH_SHORT).show();
                        }

                        // Create user
                        final String user5 = UUID.randomUUID().toString().substring(24, 32),
                                password5 = "pass0";
                        RequestBody body5 = RequestBody.create(JSON, "{\"user\":{\"username\":\"" +
                                 user5 + "\",\"password\":\"" + password5 + "\"}}\n");
                        Request request5 = new Request.Builder()
                                .url("http://vapi.appknox.io/user")
                                .post(body5)
                                .build();
                        try {
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("Adding new user...\n");
                                }
                            });
                            client.newCall(request5).execute();
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("  username:" + user5 + "\n");
                                    textViewLogs.append("  password:" + password5 + "\n");
                                }
                            });
                        } catch (IOException e) {
                            Snackbar.make(v, e.toString(), Snackbar.LENGTH_SHORT).show();
                        }

                        // Get uptime
                        try {

                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("Getting server uptime...\n");
                                }
                            });
                            Request request3 = new Request.Builder()
                                    .header("X-Auth-Token", token)
                                    .url("http://vapi.appknox.io/uptime")
                                    .build();
                            Request request4 = new Request.Builder()
                                    .header("X-Auth-Token", token)
                                    .url("http://vapi.appknox.io/uptime/s")
                                    .build();
                            client.newCall(request3).execute();
                            JSONObject json4 = new JSONObject(client.newCall(request4).execute().body().string());
                            final String uptime = json4.getJSONObject("response").getString("Output");
                            textViewLogs.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLogs.append("  system up since:" + uptime.trim() + "\n");
                                }
                            });
                        } catch (IOException|JSONException e) {
                            Snackbar.make(v, e.toString(), Snackbar.LENGTH_SHORT).show();
                        }

                        textViewLogs.post(new Runnable() {
                            @Override
                            public void run() {
                                textViewLogs.append("Done!\n");
                            }
                        });
                    }
                }).start();
            }
        });
    }
}

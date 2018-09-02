package at.cooperation.rezeptdb.service;

import android.app.Activity;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.cooperation.rezeptdb.BuildConfig;
import at.cooperation.rezeptdb.SettingsCheckActivity;
import at.cooperation.rezeptdb.model.Recipe;

public class ServerManager {
    public void loadTest(final SettingsCheckActivity activity) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = Settings.getInstance().getBaseUrl() + "test";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        activity.setServiceAvailable("\"service available\"".equals(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("server_communication", "Error contacting the server.", error);
                activity.setLoginSuccessful(false);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void loadLogin(final SettingsCheckActivity activity) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = Settings.getInstance().getBaseUrl() + "login";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        activity.setLoginSuccessful("\"login successful\"".equals(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("server_communication", "Error contacting the server.", error);
                activity.setLoginSuccessful(false);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String authString = Settings.getInstance().getAuthString();
                byte[] authEncBytes = Base64.encode(authString.getBytes(), 64);
                String authStringEnc = new String(authEncBytes);
                headers.put("Authorization", "Basic " + authStringEnc);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

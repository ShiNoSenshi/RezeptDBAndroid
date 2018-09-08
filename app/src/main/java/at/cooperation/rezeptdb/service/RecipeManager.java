package at.cooperation.rezeptdb.service;

import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.cooperation.rezeptdb.RecipesActivity;
import at.cooperation.rezeptdb.model.Recipe;

public class RecipeManager {

    public void loadRecipes(final RecipesActivity recipesView) {
        RequestQueue queue = Volley.newRequestQueue(recipesView);
        String url = Settings.getInstance(recipesView).getBaseUrl() + "recipes";


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        RecipeManager recipeManager = new RecipeManager();
                        try {
                            List<Recipe> recipes = recipeManager.readJsonStream(new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8)));
                            recipesView.setRecipes(recipes);
                        } catch (IOException e) {
                            Log.e("server_communication", "Json could not be parsed.", e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("server_communication", "Error contacting the server.", error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String authString = Settings.getInstance(recipesView).getAuthString();
                byte[] authEncBytes = Base64.encode(authString.getBytes(), 64);
                String authStringEnc = new String(authEncBytes);
                headers.put("Authorization", "Basic " + authStringEnc);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private List<Recipe> readJsonStream(InputStream in) throws IOException {
        List<Recipe> recipes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            recipes = Arrays.asList(mapper.readValue(in, Recipe[].class));
        } catch (IOException e) {
            Log.e("server_communication", "Error parsing JSON.", e);
        }

        return recipes;
    }

}

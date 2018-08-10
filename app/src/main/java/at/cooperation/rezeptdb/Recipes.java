package at.cooperation.rezeptdb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.cooperation.rezeptdb.model.Recipe;
import at.cooperation.rezeptdb.model.Tag;
import at.cooperation.rezeptdb.service.RecipeManager;

public class Recipes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        loadRecipes();
    }

    private void loadRecipes() {
        RecipeManager recipeManager = new RecipeManager();
        recipeManager.loadRecipes(this);
    }


    private void setText(String text) {
        ((TextView) findViewById(R.id.text)).setText(text);
    }

    public void setRecipes(List<Recipe> recipes) {
        StringBuilder builder = new StringBuilder();
        for (Recipe recipe: recipes) {
            builder.append(recipe.getLabel()).append("\n").append(recipe.getEffort()).append(" min");
            for (Tag tag: recipe.getTags()) {
                builder.append(" | ").append(tag.getLabel());
            }
            if(!recipe.getImages().isEmpty()) {
                builder.append("\n").append(recipe.getImages().get(0).getUrl());
            }
            builder.append("\n\n");
        }
        setText(builder.toString());
    }
}

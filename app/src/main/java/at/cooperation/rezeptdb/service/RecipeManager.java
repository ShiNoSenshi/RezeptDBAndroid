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

        /*JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);
        } finally {
            reader.close();
        }*/
        return recipes;
    }

    /*private List<Recipe> readMessagesArray(JsonReader reader) throws IOException {
        List<Recipe> messages = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    private Recipe readMessage(JsonReader reader) throws IOException {
        long id = -1;
        String label = null;
        String description = null;
        int effort = 0;
        List<Tag> tags = null;
        List<Image> images = null;
        List<IngredientGroup> ingredientGroups = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextLong();
            } else if (name.equals("label")) {
                label = reader.nextString();
            } else if (name.equals("effort")) {
                effort = reader.nextInt();
            } else if (name.equals("tags") && reader.peek() != JsonToken.NULL) {
                tags = readTagsArray(reader);
            } else if (name.equals("images") && reader.peek() != JsonToken.NULL) {
                images = readImagesArray(reader);
            } else if (name.equals("ingredient_groups") && reader.peek() != JsonToken.NULL) {
                ingredientGroups = readIngredientGroupsArray(reader);
            } else if (name.equals("description")) {
                description = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Recipe(id, label, description, effort, tags, images, ingredientGroups);
    }

    private List<IngredientGroup> readIngredientGroupsArray(JsonReader reader) throws IOException {
        List<IngredientGroup> ingredientGroups = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            ingredientGroups.add(readIngredientGroups(reader));
        }
        reader.endArray();
        return ingredientGroups;
    }

    private List<Ingredient> readIngredientsArray(JsonReader reader) throws IOException {
        List<Ingredient> ingredients = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            ingredients.add(readIngredients(reader));
        }
        reader.endArray();
        return ingredients;
    }

    private List<Tag> readTagsArray(JsonReader reader) throws IOException {
        List<Tag> tags = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            tags.add(readTag(reader));
        }
        reader.endArray();
        return tags;
    }

    private List<Image> readImagesArray(JsonReader reader) throws IOException {
        List<Image> images = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            images.add(readImages(reader));
        }
        reader.endArray();
        return images;
    }

    private IngredientGroup readIngredientGroups(JsonReader reader) throws IOException {
        IngredientGroup ingredientGroup = null;
        String label = null;
        List<Ingredient> ingredients = new ArrayList<>();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("label")) {
                label = reader.nextString();
            } else if (name.equals("ingredients") && reader.peek() != JsonToken.NULL) {
                ingredients = readIngredientsArray(reader);
            } else {
                reader.skipValue();
            }
            ingredientGroup = new IngredientGroup(label, ingredients);
        }
        reader.endObject();
        return ingredientGroup;
    }

    private Ingredient readIngredients(JsonReader reader) throws IOException {
        Ingredient ingredient = null;
        String label = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("label")) {
                label = reader.nextString();
            } else {
                reader.skipValue();
            }
            ingredient = new Ingredient(label);
        }
        reader.endObject();
        return ingredient;
    }

    private Tag readTag(JsonReader reader) throws IOException {
        Tag tag = null;
        long id = -1;
        String label = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextLong();
            } else if (name.equals("label")) {
                label = reader.nextString();
            } else {
                reader.skipValue();
            }
            tag = new Tag(id, label);
        }
        reader.endObject();
        return tag;
    }

    private Image readImages(JsonReader reader) throws IOException {
        Image image = null;
        long id = -1;
        String url = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextLong();
            } else if (name.equals("url")) {
                url = reader.nextString();
            } else {
                reader.skipValue();
            }
            image = new Image(id, url);
        }
        reader.endObject();
        return image;
    }*/
}

package at.cooperation.rezeptdb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

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
        for (Recipe recipe : recipes) {
            builder.append(recipe.getLabel()).append("\n").append(recipe.getEffort()).append(" min");
            for (Tag tag : recipe.getTags()) {
                builder.append(" | ").append(tag.getLabel());
            }
            if (!recipe.getImages().isEmpty()) {
                builder.append("\n").append(recipe.getImages().get(0).getUrl());
            }
            builder.append("\n\n");
        }
        setText(builder.toString());
    }
}

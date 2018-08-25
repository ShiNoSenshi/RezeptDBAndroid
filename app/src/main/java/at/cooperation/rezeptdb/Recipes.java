package at.cooperation.rezeptdb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import at.cooperation.rezeptdb.android.RecipeArrayAdapter;
import at.cooperation.rezeptdb.android.RecipeClickListener;
import at.cooperation.rezeptdb.model.Recipe;
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

    public void setRecipes(List<Recipe> recipes) {
        final ListView listview = findViewById(R.id.listview);
        final RecipeArrayAdapter adapter =
                new RecipeArrayAdapter(this, recipes.toArray(new Recipe[recipes.size()]));
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new RecipeClickListener());
    }
}

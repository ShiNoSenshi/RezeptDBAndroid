package at.cooperation.rezeptdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import at.cooperation.rezeptdb.android.DownloadImageTask;
import at.cooperation.rezeptdb.android.IngredientArrayAdapter;
import at.cooperation.rezeptdb.android.IngredientGroupArrayAdapter;
import at.cooperation.rezeptdb.android.ListViewHeightCalculator;
import at.cooperation.rezeptdb.model.IngredientGroup;
import at.cooperation.rezeptdb.model.Recipe;
import at.cooperation.rezeptdb.model.Tag;
import at.cooperation.rezeptdb.model.Ingredient;

public class RecipeActivity extends Activity {

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        
        Intent intent = getIntent();
        recipe = (Recipe) intent.getSerializableExtra("recipe");

        loadContent();
    }

    private void loadContent() {
        ImageView imageView = findViewById(R.id.image);
        if (!recipe.getImages().isEmpty()) {
            new DownloadImageTask(imageView)
                    .execute(recipe.getImages().get(0).getUrl());
        }

        TextView label = findViewById(R.id.label);
        label.setText(recipe.getLabel());
        TextView description = findViewById(R.id.description);
        description.setText(recipe.getDescription());

        TextView effort = findViewById(R.id.effort);
        effort.setText("Zubereitungszeit: " + recipe.getEffort() + " min");

        TextView tags = findViewById(R.id.tags);
        StringBuilder builder = new StringBuilder();
        for (Tag tag: recipe.getTags()) {
            if(builder.length() != 0)
                builder.append("; ");
            builder.append(tag.getLabel());
        }
        tags.setText(builder.toString());


        final ListView listview = findViewById(R.id.listview);
        final IngredientGroupArrayAdapter adapter =
                new IngredientGroupArrayAdapter(this, recipe.getIngredientGroups().toArray(new IngredientGroup[recipe.getIngredientGroups().size()]));
        listview.setAdapter(adapter);
        ListViewHeightCalculator.setListViewHeightBasedOnChildren(listview);

    }
}

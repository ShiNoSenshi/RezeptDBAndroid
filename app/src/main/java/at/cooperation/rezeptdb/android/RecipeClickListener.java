package at.cooperation.rezeptdb.android;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import at.cooperation.rezeptdb.RecipeActivity;
import at.cooperation.rezeptdb.model.Recipe;

public class RecipeClickListener implements android.widget.AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Recipe recipe = (Recipe) adapterView.getItemAtPosition(position);
        Intent intent = new Intent(adapterView.getContext(), RecipeActivity.class);
        intent.putExtra("recipe", recipe);
        adapterView.getContext().startActivity(intent);
    }
}

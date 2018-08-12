package at.cooperation.rezeptdb.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import at.cooperation.rezeptdb.R;
import at.cooperation.rezeptdb.model.Recipe;
import at.cooperation.rezeptdb.model.Tag;

public class RecipeArrayAdapter extends ArrayAdapter<Recipe>{
    private final Context context;
    private final Recipe[] values;

    public RecipeArrayAdapter(Context context, Recipe[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.recipe_row, parent, false);
        TextView labelView = rowView.findViewById(R.id.label);
        TextView descriptionView = rowView.findViewById(R.id.description);
        ImageView imageView = rowView.findViewById(R.id.icon);

        Recipe recipe = values[position];

        labelView.setText(recipe.getLabel());
        StringBuilder builder = new StringBuilder();
        builder.append(recipe.getEffort()).append(" min");
        for (Tag tag : recipe.getTags()) {
            builder.append(" | ").append(tag.getLabel());
        }
        descriptionView.setText(builder.toString());
        if (!recipe.getImages().isEmpty()) {
            new DownloadImageTask(imageView)
                    .execute(recipe.getImages().get(0).getUrl());
        }

        return rowView;
    }
}

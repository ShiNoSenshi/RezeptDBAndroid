package at.cooperation.rezeptdb.android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import at.cooperation.rezeptdb.R;
import at.cooperation.rezeptdb.model.Ingredient;
import at.cooperation.rezeptdb.model.IngredientGroup;

public class IngredientGroupArrayAdapter extends ArrayAdapter<IngredientGroup>{
    private final Context context;
    private final IngredientGroup[] values;

    public IngredientGroupArrayAdapter(Context context, IngredientGroup[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ingredient_group_row, parent, false);
        TextView labelView = rowView.findViewById(R.id.label);

        Log.e("test", "Gruppen: " + values.length);

        IngredientGroup ingredientGroup = values[position];
        labelView.setText(ingredientGroup.getLabel());

        Log.e("test", "Zutaten: " + ingredientGroup.getIngredients().size());

        final ListView listview = rowView.findViewById(R.id.ingredient_listview);
        final IngredientArrayAdapter adapter =
                new IngredientArrayAdapter(context, ingredientGroup.getIngredients().toArray(new Ingredient[ingredientGroup.getIngredients().size()]));
        listview.setAdapter(adapter);
        ListViewHeightCalculator.setListViewHeightBasedOnChildren(listview);


        return rowView;
    }
}

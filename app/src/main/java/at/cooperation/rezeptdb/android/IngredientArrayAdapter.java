package at.cooperation.rezeptdb.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import at.cooperation.rezeptdb.R;
import at.cooperation.rezeptdb.model.Ingredient;
import at.cooperation.rezeptdb.model.Recipe;
import at.cooperation.rezeptdb.model.Tag;

public class IngredientArrayAdapter extends ArrayAdapter<Ingredient>{
    private final Context context;
    private final Ingredient[] values;

    public IngredientArrayAdapter(Context context, Ingredient[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ingredient_row, parent, false);
        TextView amountView = rowView.findViewById(R.id.amount);
        TextView labelView = rowView.findViewById(R.id.label);

        Ingredient ingredient = values[position];

        amountView.setText(ingredient.getAmount());
        labelView.setText(ingredient.getLabel());

        return rowView;
    }
}

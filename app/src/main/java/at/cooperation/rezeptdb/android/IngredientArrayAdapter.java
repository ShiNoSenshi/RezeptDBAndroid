package at.cooperation.rezeptdb.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import at.cooperation.rezeptdb.R;

public class IngredientArrayAdapter extends ArrayAdapter<String>{
    private final Context context;
    private final String[] values;

    public IngredientArrayAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ingredient_row, parent, false);
        TextView labelView = rowView.findViewById(R.id.label);

        String ingredient = values[position];

        labelView.setText(ingredient);

        return rowView;
    }
}

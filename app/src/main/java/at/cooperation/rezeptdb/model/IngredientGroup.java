package at.cooperation.rezeptdb.model;

import java.io.Serializable;
import java.util.List;

public class IngredientGroup implements Serializable {
    private final String label;
    private final List<Ingredient> ingredients;

    public IngredientGroup(String label, List<Ingredient> ingredients) {
        this.label = label;
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() { return ingredients; }
    public String getLabel() { return label; }
}

package at.cooperation.rezeptdb.model;

import java.io.Serializable;
import java.util.List;

public class IngredientGroup implements Serializable {
    private String label;
    private List<String> ingredients;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}

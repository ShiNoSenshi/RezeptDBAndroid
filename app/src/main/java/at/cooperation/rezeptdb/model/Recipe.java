package at.cooperation.rezeptdb.model;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private String label;
    private String description;
    private int effort;
    private List<String> tags;
    private String image;
    private List<IngredientGroup> ingredient_groups;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<IngredientGroup> getIngredient_groups() {
        return ingredient_groups;
    }

    public void setIngredient_groups(List<IngredientGroup> ingredient_groups) {
        this.ingredient_groups = ingredient_groups;
    }
}

package at.cooperation.rezeptdb.model;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {


    private final long id;
    private final String label;
    private final String description;
    private final int effort;
    private final List<Tag> tags;
    private final List<Image> images;
    private final List<Ingredient> ingredients;

    public Recipe(long id, String label, String description, int effort, List<Tag> tags,
                  List<Image> images, List<Ingredient> ingredients) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.effort = effort;
        this.tags = tags;
        this.images = images;
        this.ingredients = ingredients;
    }

    public String getLabel() {
        return label;
    }

    public int getEffort() {
        return effort;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getDescription() { return description; }

    public List<Ingredient> getIngredients() { return ingredients; }
}

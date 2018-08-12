package at.cooperation.rezeptdb.model;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {


    private final long id;
    private final String label;
    private final int effort;
    private final List<Tag> tags;
    private final List<Image> images;

    public Recipe(long id, String label, int effort, List<Tag> tags, List<Image> images) {
        this.id = id;
        this.label = label;
        this.effort = effort;
        this.tags = tags;
        this.images = images;
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
}

package at.cooperation.rezeptdb.model;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private final String label;

    public Ingredient(String label) {
        this.label = label;
    }

    public String getLabel() { return label; }
}

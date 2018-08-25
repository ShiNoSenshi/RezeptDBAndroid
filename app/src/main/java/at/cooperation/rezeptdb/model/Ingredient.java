package at.cooperation.rezeptdb.model;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private final String amount;
    private final String label;

    public Ingredient(String amount, String label) {
        this.amount = amount;
        this.label = label;
    }

    public String getAmount() { return amount; }
    public String getLabel() { return label; }
}

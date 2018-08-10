package at.cooperation.rezeptdb.model;

public class Tag {

    private final long id;
    private final String label;

    public Tag(long id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public long getId() {
        return id;
    }
}

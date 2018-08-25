package at.cooperation.rezeptdb.model;

import java.io.Serializable;

public class Image implements Serializable {

    private final long id;
    private final String url;

    public Image(long id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

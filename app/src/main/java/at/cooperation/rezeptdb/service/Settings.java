package at.cooperation.rezeptdb.service;

import at.cooperation.rezeptdb.BuildConfig;

public class Settings {

    private String baseUrl;
    private String username;
    private String password;
    private static Settings instance;

    private Settings() {
        baseUrl = BuildConfig.BASE_URL;
        username = BuildConfig.API_USER;
        password = BuildConfig.API_PASSWORD;
    }

    public static Settings getInstance() {
        if(instance == null)
            instance = new Settings();
        return instance;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthString() {
        return username + ":" + password;
    }
}

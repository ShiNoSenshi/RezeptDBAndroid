package at.cooperation.rezeptdb.service;

import android.content.Context;
import android.content.SharedPreferences;

import at.cooperation.rezeptdb.BuildConfig;
import at.cooperation.rezeptdb.R;

public class Settings {

    private String baseUrl;
    private String username;
    private String password;
    private SharedPreferences sharedPref;
    private static Settings instance;

    private Settings(Context context) {
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        baseUrl = sharedPref.getString("url", BuildConfig.BASE_URL);
        username = sharedPref.getString("username", BuildConfig.API_USER);
        password = sharedPref.getString("password", BuildConfig.API_PASSWORD);
    }

    public static Settings getInstance(Context context) {
        if(instance == null)
            instance = new Settings(context);
        return instance;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        saveSetting("url", baseUrl);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        saveSetting("username", username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        saveSetting("password", password);
    }

    public String getAuthString() {
        return username + ":" + password;
    }

    private void saveSetting(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }
}

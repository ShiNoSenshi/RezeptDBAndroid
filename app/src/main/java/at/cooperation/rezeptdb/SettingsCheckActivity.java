package at.cooperation.rezeptdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import at.cooperation.rezeptdb.service.ServerManager;

public class SettingsCheckActivity extends Activity {
    private final ServerManager manager = new ServerManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_check);
        connectToServer();
    }

    private void connectToServer() {
        manager.loadTest(this);
    }

    public void setServiceAvailable(boolean available) {
        if(available)
            manager.loadLogin(this);
        else
            startActivity(new Intent(this, SettingsActivity.class));
    }

    public void setLoginSuccessful(boolean successful) {
        if(successful)
            startActivity(new Intent(this, RecipesActivity.class));
        else
            startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onBackPressed() {
        //do nothing.
    }
}

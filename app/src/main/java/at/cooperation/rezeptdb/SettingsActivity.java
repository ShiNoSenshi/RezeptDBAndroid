package at.cooperation.rezeptdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import at.cooperation.rezeptdb.service.Settings;

/**
 * A login screen that offers login via email/password.
 */
public class SettingsActivity extends Activity {

    // UI references.
    private EditText mServerView;
    private EditText mUsernameView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // Set up the login form.
        mServerView = findViewById(R.id.url);
        mServerView.setText(Settings.getInstance(this).getBaseUrl());
        mUsernameView = findViewById(R.id.username);
        mUsernameView.setText(Settings.getInstance(this).getUsername());
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setText(Settings.getInstance(this).getPassword());
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    saveSettings();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = findViewById(R.id.sign_in);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void saveSettings() {
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String url = mServerView.getText().toString();
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(url)) {
            mServerView.setError(getString(R.string.error_field_required));
            focusView = mServerView;
            cancel = true;
        }
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }



        if (cancel) {
            focusView.requestFocus();
        } else {
            Settings.getInstance(this).setBaseUrl(url);
            Settings.getInstance(this).setUsername(username);
            Settings.getInstance(this).setPassword(password);
            startActivity(new Intent(this, SettingsCheckActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        //do nothing.
    }

}


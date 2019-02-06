package com.justmobiledev.securepreferenceslib;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.justmobiledev.secure_preferences.SecurePrefsBuilder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // CONSTANTS
    private static final String SECURE_PREFS_FILE_NAME = "my_secure_prefs_file";
    private static final String SECURE_PREFS_STRING_KEY = "my_secret_string_key";

    // Members
    private SharedPreferences securePreferences;

    // UI fields
    private EditText editTextPrefs;
    private TextView textViewRetrievedPrefs;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Bind UI controls
        editTextPrefs = findViewById(R.id.edittext_pref_value);
        textViewRetrievedPrefs = findViewById(R.id.textview_retrieved_pref_value);
        startButton = findViewById(R.id.button_start);

        // Setup secure preferences
        securePreferences = new SecurePrefsBuilder()
                .setApplication(MyApplication.getInstance())
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName(SECURE_PREFS_FILE_NAME)
                .createSharedPrefs();


        // Register listeners
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeRetrieveSecurePreferences();
            }
        });

    }

    private void storeRetrieveSecurePreferences(){

        // Get a preference editor
        SharedPreferences.Editor editor = securePreferences.edit();

        // Get prefs from UI
        String prefValue = editTextPrefs.getText().toString();

        editor.putString(SECURE_PREFS_STRING_KEY, prefValue);
        editor.commit();

        String prefValueRetrieved = securePreferences.getString(SECURE_PREFS_STRING_KEY, "");

        textViewRetrievedPrefs.setText(prefValueRetrieved);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

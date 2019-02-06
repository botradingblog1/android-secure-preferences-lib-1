package com.justmobiledev.secure_preferences;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SecurePrefsBuilder {
    private Application application;
    private String sharedPrefFileName = "secure_shared_prefs";
    private boolean isObfuscated;
    private boolean isKeyObfuscated = true;

    public SecurePrefsBuilder() {
    }

    public SecurePrefsBuilder setApplication(Application application) {
        this.application = application;
        return this;
    }

    public SecurePrefsBuilder obfuscateValue(boolean obfuscated) {
        this.isObfuscated = obfuscated;
        return this;
    }

    public SecurePrefsBuilder obfuscateKey(boolean obfuscateKey) {
        this.isKeyObfuscated = obfuscateKey;
        return this;
    }

    public SecurePrefsBuilder setSharePrefFileName(String fileName) {
        this.sharedPrefFileName = fileName;
        return this;
    }

    public SharedPreferences createSharedPrefs(Context context) {
        SharedPreferences sharedPrefDelegate = context.getSharedPreferences(this.sharedPrefFileName, 0);
        Object sharedPreferences;
        if (!this.isObfuscated && !this.isKeyObfuscated) {
            sharedPreferences = sharedPrefDelegate;
        } else {
            sharedPreferences = new SecurePrefs(context, sharedPrefDelegate, this.isKeyObfuscated);
        }

        return (SharedPreferences)sharedPreferences;
    }

}

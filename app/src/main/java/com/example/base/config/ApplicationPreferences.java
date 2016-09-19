package com.example.base.config;

import android.content.SharedPreferences;

public class ApplicationPreferences {
    private final SharedPreferences preferences;

    ApplicationPreferences(SharedPreferences sharedPreferences) {
        preferences = sharedPreferences;
    }
}
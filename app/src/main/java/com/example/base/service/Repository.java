package com.example.base.service;

import com.example.base.config.ApplicationPreferences;

/**
 * Created by Wagos.
 */

public class Repository {
    private final ApplicationPreferences preferences;

    public Repository(ApplicationPreferences preferences) {
        this.preferences = preferences;
    }

    public ApplicationPreferences getPreferences() {
        return preferences;
    }
}

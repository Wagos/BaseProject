package com.example.base.service;

import android.content.Context;

import com.example.base.config.SettingsPrefs;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.Getter;

/**
 * Created by Wagos.
 */

@Getter
@Singleton
public class Repository {
    private final SettingsPrefs preferences;

    @Inject
    public Repository(Context context) {
        this.preferences = SettingsPrefs.get(context);
    }
}

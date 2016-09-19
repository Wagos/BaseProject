package com.example.base.service;

import com.example.base.api.BaseApi;
import com.example.base.config.ApplicationPreferences;

/**
 * Created by Wagos.
 */
public class BaseService {
    private final BaseApi api;
    private final ApplicationPreferences preferences;
    private final Repository reposytory;

    public BaseService(BaseApi api, ApplicationPreferences preferences, Repository reposytory) {
        this.api = api;
        this.preferences = preferences;
        this.reposytory = reposytory;
    }
}

package com.example.base.service;

import com.example.base.api.BaseApi;
import com.example.base.config.ApplicationPreferences;

import lombok.AllArgsConstructor;

/**
 * Created by Wagos.
 */
@AllArgsConstructor
public class BaseService {
    private BaseApi api;
    private ApplicationPreferences preferences;
    private Repository reposytory;
}

package com.example.base.service;

import com.example.base.api.BaseApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.RequiredArgsConstructor;

/**
 * Created by Wagos.
 */
@Singleton
public class BaseService {
    private final BaseApi api;
    private final Repository reposytory;

    @Inject
    public BaseService(BaseApi api, Repository reposytory) {
        this.api = api;
        this.reposytory = reposytory;
    }
}

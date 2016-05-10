package com.example.base.service;

import com.example.base.config.ApplicationPreferences;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by Wagos.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class Repository {
    private final ApplicationPreferences preferences;
}

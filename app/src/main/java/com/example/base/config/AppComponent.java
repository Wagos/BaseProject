package com.example.base.config;

import com.example.base.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
package com.example.base.config;

import android.app.Application;
import android.content.Context;

import com.example.base.api.BaseApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@SuppressWarnings("unused")
public class ApplicationModule {
    private final Application application;
    private static final String NO_BACKUP_PREFERENCES = "no_backup_preferences";

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    static BaseApi provideBaseApi(Context context, Gson gson) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        int cacheSize = 1024 * 1024; // 1 MiB
        File cacheDirectory = context.getCacheDir();
        Cache cache = new Cache(cacheDirectory, cacheSize);
        if (cacheDirectory != null && cacheDirectory.exists()) {
            builder.cache(cache);
        }

        OkHttpClient client = builder.build();
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BaseApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return restAdapter.create(BaseApi.class);
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return new GsonBuilder()
                .create();
    }
}



package com.example.base.config;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.base.api.BaseApi;
import com.example.base.service.BaseService;
import com.example.base.service.Repository;
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
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public ApplicationPreferences provideApplicationPreferences(Gson gson) {
        return new ApplicationPreferences(PreferenceManager.getDefaultSharedPreferences(application));
    }

    @Provides
    @Singleton
    public SharedPreferences provideNoBackUpPreferences(){
        return application.getSharedPreferences(NO_BACKUP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public BaseService provideBaseService(BaseApi api, ApplicationPreferences preferences,
                                          Repository repository) {
        return new BaseService(api, preferences, repository);
    }

    @Provides
    @Singleton
    public BaseApi provideBaseApi(Context context, Gson gson, ApplicationPreferences preferences) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        int cacheSize = 1024 * 1024; // 1 MiB
        File cacheDirectory = context.getCacheDir();
        Cache cache = new Cache(cacheDirectory, cacheSize);
        if (cacheDirectory != null && cacheDirectory.exists()) {
            builder.cache(cache);
        }

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BaseApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build())
                .build();
        return restAdapter.create(BaseApi.class);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    public Repository provideRepository(ApplicationPreferences preferences) {
        return new Repository(preferences);
    }
}



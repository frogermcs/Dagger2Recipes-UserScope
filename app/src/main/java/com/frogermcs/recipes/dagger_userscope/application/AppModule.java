package com.frogermcs.recipes.dagger_userscope.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.frogermcs.recipes.dagger_userscope.api.GithubApiService;
import com.frogermcs.recipes.dagger_userscope.user.UserComponent;
import com.frogermcs.recipes.dagger_userscope.user.UserDataStore;
import com.frogermcs.recipes.dagger_userscope.user.UserManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public UserManager provideUserManager(GithubApiService githubApiService, UserDataStore userDataStore,
                                          UserComponent.Builder userComponentBuilder) {
        return new UserManager(githubApiService, userDataStore, userComponentBuilder);
    }

    @Provides
    @Singleton
    UserDataStore provideUserDataStore(SharedPreferences sharedPreferences, Gson gson) {
        return new UserDataStore(sharedPreferences, gson);
    }
}
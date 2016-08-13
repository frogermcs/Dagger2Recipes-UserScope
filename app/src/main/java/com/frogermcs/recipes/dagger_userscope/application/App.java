package com.frogermcs.recipes.dagger_userscope.application;

import android.app.Application;
import android.content.Context;

import com.frogermcs.recipes.dagger_userscope.api.GithubApiModule;

import timber.log.Timber;

/**
 * Created by froger_mcs on 09/08/16.
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        setupAppComponent();
    }

    private void setupAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .githubApiModule(new GithubApiModule())
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        return get(context).appComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}

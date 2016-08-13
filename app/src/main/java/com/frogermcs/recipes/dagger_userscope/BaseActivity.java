package com.frogermcs.recipes.dagger_userscope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.frogermcs.recipes.dagger_userscope.application.App;
import com.frogermcs.recipes.dagger_userscope.application.AppComponent;

/**
 * Created by froger_mcs on 09/08/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(App.getAppComponent(this));
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
}

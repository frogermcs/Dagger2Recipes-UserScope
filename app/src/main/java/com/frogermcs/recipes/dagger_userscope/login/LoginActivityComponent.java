package com.frogermcs.recipes.dagger_userscope.login;

import com.frogermcs.recipes.dagger_userscope.BaseActivityModule;
import com.frogermcs.recipes.dagger_userscope.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by froger_mcs on 09/08/16.
 */

@ActivityScope
@Subcomponent(
        modules = LoginActivityComponent.LoginActivityModule.class
)
public interface LoginActivityComponent {
    LoginActivity inject(LoginActivity activity);

    @Module
    class LoginActivityModule extends BaseActivityModule<LoginActivity> {
        LoginActivityModule(LoginActivity activity) {
            super(activity);
        }
    }
}
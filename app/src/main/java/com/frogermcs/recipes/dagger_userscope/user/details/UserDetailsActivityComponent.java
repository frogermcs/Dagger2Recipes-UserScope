package com.frogermcs.recipes.dagger_userscope.user.details;

import com.frogermcs.recipes.dagger_userscope.BaseActivityModule;
import com.frogermcs.recipes.dagger_userscope.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by froger_mcs on 09/08/16.
 */

@ActivityScope
@Subcomponent(
        modules = UserDetailsActivityComponent.UserDetailsActivityModule.class
)
public interface UserDetailsActivityComponent {
    UserDetailsActivity inject(UserDetailsActivity activity);

    @Module
    class UserDetailsActivityModule extends BaseActivityModule<UserDetailsActivity> {
        UserDetailsActivityModule(UserDetailsActivity activity) {
            super(activity);
        }
    }
}
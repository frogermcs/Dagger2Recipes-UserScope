package com.frogermcs.recipes.dagger_userscope.user;

import com.frogermcs.recipes.dagger_userscope.di.scopes.UserScope;
import com.frogermcs.recipes.dagger_userscope.user.details.UserDetailsActivityComponent;
import com.frogermcs.recipes.dagger_userscope.user.repositories.RepositoriesListActivityComponent;

import dagger.Subcomponent;

/**
 * Created by froger_mcs on 09/08/16.
 */

@UserScope
@Subcomponent(
        modules = {
                UserModule.class
        }
)
public interface UserComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder sessionModule(UserModule userModule);

        UserComponent build();
    }

    UserDetailsActivityComponent plus(UserDetailsActivityComponent.UserDetailsActivityModule module);

    RepositoriesListActivityComponent plus(RepositoriesListActivityComponent.RepositoriesListActivityModule module);

    LogoutManager logoutManager();
}
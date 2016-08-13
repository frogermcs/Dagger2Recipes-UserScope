package com.frogermcs.recipes.dagger_userscope.user.repositories;

import com.frogermcs.recipes.dagger_userscope.BaseActivityModule;
import com.frogermcs.recipes.dagger_userscope.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by froger_mcs on 13/08/16.
 */

@ActivityScope
@Subcomponent(
        modules = RepositoriesListActivityComponent.RepositoriesListActivityModule.class
)
public interface RepositoriesListActivityComponent {
    RepositoriesListActivity inject(RepositoriesListActivity activity);

    @Module
    class RepositoriesListActivityModule extends BaseActivityModule<RepositoriesListActivity> {
        RepositoriesListActivityModule(RepositoriesListActivity activity) {
            super(activity);
        }

        @Provides
        @ActivityScope
        RepositoriesListAdapter repositoriesListAdapter() {
            return new RepositoriesListAdapter(activity);
        }
    }
}
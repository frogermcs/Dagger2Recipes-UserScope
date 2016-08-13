package com.frogermcs.recipes.dagger_userscope.user.repositories;

import com.frogermcs.recipes.dagger_userscope.api.SimpleApiObserver;
import com.frogermcs.recipes.dagger_userscope.di.scopes.ActivityScope;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by froger_mcs on 13/08/16.
 */

@ActivityScope
public class RepositoriesListActivityPresenter {
    private RepositoriesListActivity activity;
    private RepositoriesManager repositoriesManager;

    @Inject
    public RepositoriesListActivityPresenter(RepositoriesListActivity activity, RepositoriesManager repositoriesManager) {
        this.activity = activity;
        this.repositoriesManager = repositoriesManager;
    }

    public void loadRepositories() {
        activity.showLoading(true);
        repositoriesManager.getUsersRepositories().subscribe(new SimpleApiObserver<List<String>>() {
            @Override
            public void onNext(List<String> repositories) {
                activity.setRepositories(repositories);
                activity.showLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                activity.showLoading(false);
            }
        });
    }
}

package com.frogermcs.recipes.dagger_userscope.user;

import android.app.Application;

import com.frogermcs.recipes.dagger_userscope.api.GithubApiService;
import com.frogermcs.recipes.dagger_userscope.di.scopes.UserScope;
import com.frogermcs.recipes.dagger_userscope.user.model.User;
import com.frogermcs.recipes.dagger_userscope.user.repositories.RepositoriesManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miroslaw Stanek on 23.06.15.
 */
@Module
public class UserModule {

    private User user;

    public UserModule(User user) {
        this.user = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return user;
    }

    @Provides
    @UserScope
    LogoutManager provideLogoutManager(Application application) {
        return new LogoutManager(application, user);
    }

    @Provides
    @UserScope
    RepositoriesManager provideRepositoriesManager(GithubApiService githubApiService) {
        return new RepositoriesManager(user, githubApiService);
    }
}

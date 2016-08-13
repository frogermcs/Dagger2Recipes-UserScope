package com.frogermcs.recipes.dagger_userscope.user;

import com.frogermcs.recipes.dagger_userscope.api.GithubApiService;
import com.frogermcs.recipes.dagger_userscope.user.model.User;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class UserManager {

    private GithubApiService githubApiService;
    private UserDataStore userDataStore;
    private UserComponent.Builder userComponentBuilder;

    private UserComponent userComponent;

    public UserManager(GithubApiService githubApiService, UserDataStore userDataStore,
                       UserComponent.Builder userComponentBuilder) {
        this.githubApiService = githubApiService;
        this.userDataStore = userDataStore;
        this.userComponentBuilder = userComponentBuilder;
    }

    public Observable<User> startSessionForUser(String username) {
        return githubApiService.getUser(username)
                .map(User.UserResponseToUser())
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        userDataStore.createUser(user);
                        startUserSession();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private boolean startUserSession() {
        User user = userDataStore.getUser();
        if (user != null) {
            Timber.i("Session started, user: %s", user);
            userComponent = userComponentBuilder.sessionModule(new UserModule(user)).build();
            return true;
        }

        return false;
    }

    public void closeUserSession() {
        Timber.i("Close session for user: %s", userDataStore.getUser());
        userComponent.logoutManager().startLogoutProcess();
        userDataStore.clearUser();
        userComponent = null;
    }

    public boolean isUserSessionStartedOrStartSessionIfPossible() {
        return userComponent != null || startUserSession();
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

}

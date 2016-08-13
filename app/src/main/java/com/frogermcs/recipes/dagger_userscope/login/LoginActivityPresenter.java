package com.frogermcs.recipes.dagger_userscope.login;

import com.frogermcs.recipes.dagger_userscope.api.SimpleApiObserver;
import com.frogermcs.recipes.dagger_userscope.di.scopes.ActivityScope;
import com.frogermcs.recipes.dagger_userscope.user.UserManager;
import com.frogermcs.recipes.dagger_userscope.user.model.User;

import javax.inject.Inject;

/**
 * Created by froger_mcs on 09/08/16.
 */

@ActivityScope
public class LoginActivityPresenter {

    private LoginActivity activity;
    private UserManager userManager;

    public String username;

    @Inject
    public LoginActivityPresenter(LoginActivity activity, UserManager userManager) {
        this.activity = activity;
        this.userManager = userManager;
    }

    public void onStartSessionClick() {
        activity.showLoading(true);
        userManager.startSessionForUser(username).subscribe(new SimpleApiObserver<User>() {
            @Override
            public void onNext(User user) {
                activity.showLoading(false);
                activity.navigateToUserDetails();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                activity.showLoading(false);
                activity.showValidationError();
            }
        });
    }
}
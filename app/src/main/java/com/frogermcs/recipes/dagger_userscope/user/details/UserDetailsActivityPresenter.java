package com.frogermcs.recipes.dagger_userscope.user.details;

import com.frogermcs.recipes.dagger_userscope.di.scopes.ActivityScope;
import com.frogermcs.recipes.dagger_userscope.user.UserManager;
import com.frogermcs.recipes.dagger_userscope.user.model.User;

import javax.inject.Inject;

/**
 * Created by froger_mcs on 11/08/16.
 */

@ActivityScope
public class UserDetailsActivityPresenter {
    private UserDetailsActivity activity;
    private UserManager userManager;
    private User user;

    @Inject
    public UserDetailsActivityPresenter(UserDetailsActivity activity, UserManager userManager, User user) {
        this.activity = activity;
        this.userManager = userManager;
        this.user = user;
    }

    public void onCreate() {
        activity.setUserName(user.login);
        activity.setUserUrl(user.url);
    }

    public void onFinish() {
        userManager.closeUserSession();
    }

    public void onLogoutClick() {
        activity.finish();
    }
}

package com.frogermcs.recipes.dagger_userscope.user;

import android.support.annotation.Nullable;

import com.frogermcs.recipes.dagger_userscope.BaseActivity;
import com.frogermcs.recipes.dagger_userscope.application.AppComponent;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by froger_mcs on 11/08/16.
 */

public abstract class BaseUserActivity extends BaseActivity {

    @Inject
    UserManager userManager;

    private boolean isUserSessionStarted;
    private boolean isSessionStartedCalled;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
        setupUserComponent();
    }

    private void setupUserComponent() {
        isUserSessionStarted = userManager.isUserSessionStartedOrStartSessionIfPossible();
        onUserComponentSetup(userManager.getUserComponent());

        //This screen cannot work when user session is not started.
        if (!isUserSessionStarted) {
            finish();
        }
    }

    protected abstract void onUserComponentSetup(UserComponent userComponent);

    @Override
    protected void onStart() {
        super.onStart();
        if (!isSessionStartedCalled) {
            Timber.e("isUserSessionStarted() wasn't called in %s . When it returns false your @Injected dependencies can be null.", getClass().getSimpleName());
        }
    }

    public boolean isUserSessionStarted() {
        isSessionStartedCalled = true;
        return isUserSessionStarted;
    }
}

package com.frogermcs.recipes.dagger_userscope.user.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.frogermcs.recipes.dagger_userscope.R;
import com.frogermcs.recipes.dagger_userscope.user.BaseUserActivity;
import com.frogermcs.recipes.dagger_userscope.user.UserComponent;
import com.frogermcs.recipes.dagger_userscope.user.repositories.RepositoriesListActivity;

import javax.inject.Inject;

/**
 * Created by froger_mcs on 09/08/16.
 */

public class UserDetailsActivity extends BaseUserActivity {

    @Inject
    UserDetailsActivityPresenter presenter;

    private TextView tvUser;
    private TextView tvUserUrl;

    @Override
    protected void onUserComponentSetup(UserComponent userComponent) {
        userComponent.plus(new UserDetailsActivityComponent.UserDetailsActivityModule(this)).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        tvUser = (TextView) findViewById(R.id.tvUser);
        tvUserUrl = (TextView) findViewById(R.id.tvUserUrl);

        //This check has to be called. Otherwise, when user is not logged in, presenter won't be injected and this line will cause NPE
        if (isUserSessionStarted()) {
            presenter.onCreate();
        }
    }

    @Override
    public void finish() {
        super.finish();
        presenter.onFinish();
    }

    public void setUserName(String userName) {
        tvUser.setText(userName);
    }

    public void setUserUrl(String url) {
        tvUserUrl.setText(url);
    }

    public void onLogoutClick(View view) {
        presenter.onLogoutClick();
    }

    public void onRepositoriesClick(View view) {
        startActivity(new Intent(this, RepositoriesListActivity.class));
    }
}

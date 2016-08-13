package com.frogermcs.recipes.dagger_userscope.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.frogermcs.recipes.dagger_userscope.BaseActivity;
import com.frogermcs.recipes.dagger_userscope.R;
import com.frogermcs.recipes.dagger_userscope.application.AppComponent;
import com.frogermcs.recipes.dagger_userscope.user.details.UserDetailsActivity;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class LoginActivity extends BaseActivity {

    @Inject
    LoginActivityPresenter presenter;

    private EditText etUsername;
    private ProgressBar pbLoading;
    private Button btnStartSession;

    private Subscription textChangeSubscription;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.plus(new LoginActivityComponent.LoginActivityModule(this)).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        btnStartSession = (Button) findViewById(R.id.btnStartSession);

        textChangeSubscription = RxTextView.textChangeEvents(etUsername).subscribe(new Action1<TextViewTextChangeEvent>() {
            @Override
            public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                presenter.username = textViewTextChangeEvent.text().toString();
                etUsername.setError(null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textChangeSubscription.unsubscribe();
    }

    public void onStartSessionClick(View v) {
        presenter.onStartSessionClick();
    }

    public void showLoading(boolean loading) {
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
        btnStartSession.setVisibility(loading ? View.GONE : View.VISIBLE);
    }

    public void showValidationError() {
        etUsername.setError("Validation error");
    }

    public void navigateToUserDetails() {
        startActivity(new Intent(this, UserDetailsActivity.class));
    }
}

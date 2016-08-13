package com.frogermcs.recipes.dagger_userscope.user;

import android.app.Application;
import android.widget.Toast;

import com.frogermcs.recipes.dagger_userscope.api.SimpleApiObserver;
import com.frogermcs.recipes.dagger_userscope.user.model.User;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by froger_mcs on 11/08/16.
 */

public class LogoutManager {

    Application application;
    User user;

    @Inject
    public LogoutManager(Application application, User user) {
        this.application = application;
        this.user = user;
    }

    public void startLogoutProcess() {
        Toast.makeText(application, "Logging out...", Toast.LENGTH_SHORT).show();
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                for (int i = 0; i < 5; i++) {
                    Timber.i("Logging out user %s (%d)...", user, i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleApiObserver<Object>() {

                    @Override
                    public void onCompleted() {
                        Toast.makeText(application, String.format("User %s logged out...", user.login), Toast.LENGTH_SHORT).show();
                        Timber.i("Logged out");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}

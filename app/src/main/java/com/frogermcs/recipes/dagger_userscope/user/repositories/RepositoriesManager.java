package com.frogermcs.recipes.dagger_userscope.user.repositories;

import com.frogermcs.recipes.dagger_userscope.api.GithubApiService;
import com.frogermcs.recipes.dagger_userscope.api.response.RepositoryResponse;
import com.frogermcs.recipes.dagger_userscope.user.model.User;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by froger_mcs on 13/08/16.
 */

public class RepositoriesManager {
    private User user;
    private GithubApiService githubApiService;

    public RepositoriesManager(User user, GithubApiService githubApiService) {
        this.user = user;
        this.githubApiService = githubApiService;
    }

    public Observable<List<String>> getUsersRepositories() {
        return githubApiService.getUsersRepositories(user.login)
                .map(new Func1<List<RepositoryResponse>, List<String>>() {
                    @Override
                    public List<String> call(List<RepositoryResponse> repositoriesListResponse) {
                        List<String> repositories = new ArrayList<>(repositoriesListResponse.size());
                        for (RepositoryResponse repositoryResponse : repositoriesListResponse) {
                            repositories.add(repositoryResponse.name);
                        }
                        return repositories;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

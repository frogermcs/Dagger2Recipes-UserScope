package com.frogermcs.recipes.dagger_userscope.api;

import com.frogermcs.recipes.dagger_userscope.api.response.RepositoryResponse;
import com.frogermcs.recipes.dagger_userscope.api.response.UserResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public interface GithubApiService {
    @GET("/users/{username}")
    Observable<UserResponse> getUser(
            @Path("username") String username
    );

    @GET("/users/{username}/repos")
    Observable<List<RepositoryResponse>> getUsersRepositories(
            @Path("username") String username
    );
}

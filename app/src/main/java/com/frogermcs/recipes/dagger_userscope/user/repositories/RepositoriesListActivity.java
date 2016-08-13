package com.frogermcs.recipes.dagger_userscope.user.repositories;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.frogermcs.recipes.dagger_userscope.R;
import com.frogermcs.recipes.dagger_userscope.user.BaseUserActivity;
import com.frogermcs.recipes.dagger_userscope.user.UserComponent;

import java.util.List;

import javax.inject.Inject;

public class RepositoriesListActivity extends BaseUserActivity {

    @Inject
    RepositoriesListActivityPresenter presenter;
    @Inject
    RepositoriesListAdapter repositoriesListAdapter;

    private ListView lvRepositories;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);
        lvRepositories = (ListView) findViewById(R.id.lvRepositories);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        if (isUserSessionStarted()) {
            setupRepositoriesListView();
            presenter.loadRepositories();
        }
    }

    @Override
    protected void onUserComponentSetup(UserComponent userComponent) {
        userComponent.plus(new RepositoriesListActivityComponent.RepositoriesListActivityModule(this)).inject(this);
    }

    private void setupRepositoriesListView() {
        lvRepositories.setAdapter(repositoriesListAdapter);
    }

    public void showLoading(boolean loading) {
        lvRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void setRepositories(List<String> repositories) {
        repositoriesListAdapter.clear();
        repositoriesListAdapter.addAll(repositories);
    }
}

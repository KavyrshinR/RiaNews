package ru.kavyrshin.rianews.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import ru.kavyrshin.rianews.R;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.presentation.presenters.NewsListPresenter;
import ru.kavyrshin.rianews.presentation.views.NewsListView;

public class NewsListActivity extends BaseActivity implements NewsListView, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private RecyclerView rvNewsList;

    @InjectPresenter
    NewsListPresenter presenter;

    @ProvidePresenter
    NewsListPresenter providePresenter() {
        return application().getApplicationComponent().newsListComponent().build().presenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvNewsList = (RecyclerView) findViewById(R.id.rvNewsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvNewsList.setLayoutManager(linearLayoutManager);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.opened_navigation_view, R.string.closed_navigation_view);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public void showCategories(List<Category> listCategories) {
        for (int i = 0; i < listCategories.size(); i++) {
            navigationView.getMenu().add(0, i, i, listCategories.get(i).getName()).setCheckable(true);
        }
    }

    @Override
    public void showNews(List<News> listNews) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "menuItem " + item.getItemId(), Toast.LENGTH_SHORT).show();

        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }
}

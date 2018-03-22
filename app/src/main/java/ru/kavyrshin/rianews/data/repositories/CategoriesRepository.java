package ru.kavyrshin.rianews.data.repositories;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.kavyrshin.rianews.data.network.JSoupRiaNews;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.repositories.ICategoriesRepository;

public class CategoriesRepository implements ICategoriesRepository {

    private JSoupRiaNews jSoupRiaNews;

    @Inject
    public CategoriesRepository(JSoupRiaNews jSoupRiaNews) {
        this.jSoupRiaNews = jSoupRiaNews;
    }

    @Override
    public Single<List<Category>> getAllCategories() {
        return jSoupRiaNews.getAllCategories();
    }
}
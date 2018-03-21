package ru.kavyrshin.rianews.data.repositories;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.repositories.ICategoriesRepository;

public class CategoriesRepository implements ICategoriesRepository {

    @Inject
    public CategoriesRepository() {
    }

    @Override
    public Single<List<Category>> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("First", "first.com"));
        categories.add(new Category("Second", "second.com"));
        categories.add(new Category("Third", "third.com"));
        categories.add(new Category("Fourth", "fourth.com"));
        categories.add(new Category("Fifth", "fifth.com"));
        categories.add(new Category("Sixth", "sixth.com"));
        return Single.just(categories);
    }
}
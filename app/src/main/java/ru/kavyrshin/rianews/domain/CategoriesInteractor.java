package ru.kavyrshin.rianews.domain;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.repositories.ICategoriesRepository;

public class CategoriesInteractor {

    private ICategoriesRepository categoriesRepository;

    @Inject
    public CategoriesInteractor(ICategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public Single<List<Category>> getAllCategories() {
        return categoriesRepository.getAllCategories()
                .subscribeOn(Schedulers.io());
    }
}
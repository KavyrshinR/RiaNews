package ru.kavyrshin.rianews.domain;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.domain.global.repositories.ICategoriesRepository;
import ru.kavyrshin.rianews.domain.global.repositories.INewsRepository;

public class CategoriedNewsInteractor {

    private ICategoriesRepository categoriesRepository;
    private INewsRepository newsRepository;

    @Inject
    public CategoriedNewsInteractor(ICategoriesRepository categoriesRepository, INewsRepository newsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.newsRepository = newsRepository;
    }

    public Single<List<Category>> getAllCategories() {
        return categoriesRepository.getAllCategories()
                .subscribeOn(Schedulers.io());
    }

    public Single<List<News>> getNewsByCategory(int id) {
        return categoriesRepository.getCategoryById(id)
                .flatMap(new Function<Category, Single<List<News>>>() {
                    @Override
                    public Single<List<News>> apply(Category category) throws Exception {
                        return newsRepository.getNewsByCategory(category);
                    }
                }).subscribeOn(Schedulers.io());
    }
}
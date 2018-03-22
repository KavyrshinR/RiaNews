package ru.kavyrshin.rianews.data.repositories;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import ru.kavyrshin.rianews.data.network.JSoupRiaNews;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.repositories.ICategoriesRepository;

public class CategoriesRepository implements ICategoriesRepository {

    private JSoupRiaNews jSoupRiaNews;

    private List<Category> categories;

    @Inject
    public CategoriesRepository(JSoupRiaNews jSoupRiaNews) {
        this.jSoupRiaNews = jSoupRiaNews;
    }

    @Override
    public Single<List<Category>> getAllCategories() {
        return jSoupRiaNews.getAllCategories()
                .map(new Function<List<Category>, List<Category>>() {
                    @Override
                    public List<Category> apply(List<Category> categories) throws Exception {
                        for (int i = 0; i < categories.size(); i++) {
                            categories.get(i).setId(i);
                        }
                        CategoriesRepository.this.categories = categories;
                        return categories;
                    }
        });
    }

    @Override
    public Single<Category> getCategoryById(int id) {
        Category result = null;
        for (Category category : categories) {
            if (category.getId() == id) {
                result = category;
            }
        }
        return Single.just(result);
    }
}
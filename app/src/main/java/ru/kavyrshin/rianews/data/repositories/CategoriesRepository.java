package ru.kavyrshin.rianews.data.repositories;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import ru.kavyrshin.rianews.data.database.NewsDataBase;
import ru.kavyrshin.rianews.data.network.JSoupRiaNews;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.repositories.ICategoriesRepository;

public class CategoriesRepository implements ICategoriesRepository {

    private JSoupRiaNews jSoupRiaNews;
    private NewsDataBase newsDataBase;

    @Inject
    public CategoriesRepository(JSoupRiaNews jSoupRiaNews, NewsDataBase newsDataBase) {
        this.jSoupRiaNews = jSoupRiaNews;
        this.newsDataBase = newsDataBase;
    }

    @Override
    public Single<List<Category>> getAllCategories() {
        return jSoupRiaNews.getAllCategories()
                .flatMap(new Function<List<Category>, Single<List<Category>>>() {
                    @Override
                    public Single<List<Category>> apply(final List<Category> categories) throws Exception {
                        for (int i = 0; i < categories.size(); i++) {
                            categories.get(i).setId(categories.get(i).getUrl().hashCode());
                        }

                        newsDataBase.deleteAllCategories();
                        newsDataBase.saveCategories(categories);

                        return newsDataBase.getCategoriesList();
                    }
                }).onErrorResumeNext(newsDataBase.getCategoriesList());
    }

    @Override
    public Single<Category> getCategoryById(int id) {
        return newsDataBase.getCategoryById(id);
    }
}
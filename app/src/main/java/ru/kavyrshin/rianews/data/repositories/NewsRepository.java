package ru.kavyrshin.rianews.data.repositories;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.kavyrshin.rianews.data.network.JSoupRiaNews;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.domain.global.repositories.INewsRepository;

public class NewsRepository implements INewsRepository {


    private JSoupRiaNews parser;

    @Inject
    public NewsRepository(JSoupRiaNews parser) {
        this.parser = parser;
    }

    @Override
    public Single<List<News>> getNewsByCategory(Category category) {
        return parser.getNewsByCategory(category);
    }

}
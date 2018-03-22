package ru.kavyrshin.rianews.data.repositories;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.domain.global.repositories.INewsRepository;

public class NewsRepository implements INewsRepository {

    @Override
    public Single<List<News>> getNewsByCategory(Category category) {
        List<News> newsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            News news = new News();
            news.setCategory(category);
            news.setName(category.getName() + " " + i + " news");
            newsList.add(news);
        }

        return Single.just(newsList);
    }

}
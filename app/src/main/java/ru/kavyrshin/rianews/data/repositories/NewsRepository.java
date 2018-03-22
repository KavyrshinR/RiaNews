package ru.kavyrshin.rianews.data.repositories;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.domain.global.repositories.INewsRepository;

public class NewsRepository implements INewsRepository {


    @Inject
    public NewsRepository() {
    }

    @Override
    public Single<List<News>> getNewsByCategory(Category category) {
        List<News> newsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            News news = new News();
            news.setImgUrl("https://pp.userapi.com/c639420/v639420066/54873/ZGGvQbaHdso.jpg");
            news.setCategory(category);
            news.setName(category.getName() + " " + i + " news");
            newsList.add(news);
        }

        return Single.just(newsList);
    }

}
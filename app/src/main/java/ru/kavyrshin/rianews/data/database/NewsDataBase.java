package ru.kavyrshin.rianews.data.database;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;

public class NewsDataBase {

    private static NewsDataBase newsDataBase;


    private List<News> newsList = new ArrayList<>();

    public static NewsDataBase getInstance() {
        if (newsDataBase == null) {
            newsDataBase = new NewsDataBase();
        }

        return newsDataBase;
    }

    public Single<List<News>> getNewsByCategory(Category category) {
        List<News> resultList = new ArrayList<>();

        for (News item : newsList) {
            if (item.getCategory().getId() == category.getId()) {
                resultList.add(item);
            }
        }

        return Single.just(resultList);
    }

    public Single<News> getNewsById(int newsId) {
        News result = null;

        for (News item : newsList) {
            if (item.getId() == newsId) {
                result = item;
            }
        }

        return Single.just(result);
    }

    public Completable saveNewsList(List<News> newsList) {
        this.newsList = newsList;
        return Completable.complete();
    }

}
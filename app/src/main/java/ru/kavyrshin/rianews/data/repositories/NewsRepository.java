package ru.kavyrshin.rianews.data.repositories;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import ru.kavyrshin.rianews.data.database.NewsDataBase;
import ru.kavyrshin.rianews.data.network.JSoupRiaNews;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.domain.global.repositories.INewsRepository;

public class NewsRepository implements INewsRepository {


    private JSoupRiaNews parser;
    private NewsDataBase newsDataBase;


    @Inject
    public NewsRepository(JSoupRiaNews parser, NewsDataBase newsDataBase) {
        this.parser = parser;
        this.newsDataBase = newsDataBase;
    }

    @Override
    public Single<List<News>> getNewsByCategory(final Category category) {
        return parser.getNewsByCategory(category)
                .flatMap(new Function<List<News>, SingleSource<List<News>>>() {
                    @Override
                    public SingleSource<List<News>> apply(List<News> news) throws Exception {
                        for (News item : news) {
                            item.setId(item.getUrl().hashCode());
                        }

                        newsDataBase.deleteNewsByCategory(category);
                        newsDataBase.saveNewsList(news);

                        return newsDataBase.getNewsByCategory(category);
                    }
                }).onErrorResumeNext(newsDataBase.getNewsByCategory(category));
    }

    @Override
    public Single<News> getDetailedNews(int newsId) {
        return newsDataBase.getNewsById(newsId)
                .flatMap(new Function<News, SingleSource<News>>() {
                    @Override
                    public SingleSource<News> apply(News news) throws Exception {
                        return parser.getDetailedNews(news)
                                .flatMap(new Function<News, SingleSource<News>>() {
                                    @Override
                                    public SingleSource<News> apply(News news) throws Exception {
                                        newsDataBase.saveNews(news);
                                        return newsDataBase.getNewsById(news.getId());
                                    }
                                }).onErrorResumeNext(Single.just(news));
                    }
                });
    }
}
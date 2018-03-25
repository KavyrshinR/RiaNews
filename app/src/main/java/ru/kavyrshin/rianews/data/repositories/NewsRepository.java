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


    @Inject
    public NewsRepository(JSoupRiaNews parser) {
        this.parser = parser;
    }

    @Override
    public Single<List<News>> getNewsByCategory(Category category) {
        return parser.getNewsByCategory(category)
                .flatMap(new Function<List<News>, SingleSource<List<News>>>() {
                    @Override
                    public SingleSource<List<News>> apply(List<News> news) throws Exception {
                        for (News item : news) {
                            item.setId(item.getUrl().hashCode());
                        }

                        NewsDataBase.getInstance().saveNewsList(news);

                        return Single.just(news);
                    }
                });
    }

    @Override
    public Single<News> getDetailedNews(int newsId) {
        return NewsDataBase.getInstance().getNewsById(newsId)
                .flatMap(new Function<News, SingleSource<News>>() {
                    @Override
                    public SingleSource<News> apply(News news) throws Exception {
                        return parser.getDetailedNews(news);
                    }
                });
    }
}
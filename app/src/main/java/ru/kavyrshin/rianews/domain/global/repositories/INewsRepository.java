package ru.kavyrshin.rianews.domain.global.repositories;


import java.util.List;

import io.reactivex.Single;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;

public interface INewsRepository {
    Single<List<News>> getNewsByCategory(Category category);
}
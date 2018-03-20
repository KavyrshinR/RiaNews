package ru.kavyrshin.rianews.presentation.views;

import java.util.List;

import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;

public interface NewsListView extends BaseView {
    void showCategories(List<Category> listCategories);
    void showNews(List<News> listNews);

}
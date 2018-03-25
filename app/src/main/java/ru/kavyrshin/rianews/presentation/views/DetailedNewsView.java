package ru.kavyrshin.rianews.presentation.views;


import ru.kavyrshin.rianews.domain.global.models.News;

public interface DetailedNewsView extends BaseView {
    void showNews(News news);
}

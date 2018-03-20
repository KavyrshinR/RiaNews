package ru.kavyrshin.rianews.di;

import dagger.Subcomponent;
import ru.kavyrshin.rianews.presentation.presenters.NewsListPresenter;

@Subcomponent
public interface NewsListComponent {

    @Subcomponent.Builder
    interface Builder {

        NewsListComponent build();
    }

    NewsListPresenter presenter();
}
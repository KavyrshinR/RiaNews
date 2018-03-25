package ru.kavyrshin.rianews.di;


import dagger.BindsInstance;
import dagger.Subcomponent;
import ru.kavyrshin.rianews.presentation.presenters.DetailedNewsPresenter;

@Subcomponent
public interface DetailedNewsComponent {

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        Builder newsId(int newsId);

        DetailedNewsComponent build();
    }

    DetailedNewsPresenter presenter();
}
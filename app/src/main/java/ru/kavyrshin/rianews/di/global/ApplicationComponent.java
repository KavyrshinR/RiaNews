package ru.kavyrshin.rianews.di.global;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import ru.kavyrshin.rianews.RiaNewsApplication;
import ru.kavyrshin.rianews.di.DetailedNewsComponent;
import ru.kavyrshin.rianews.di.NewsListComponent;
import ru.kavyrshin.rianews.di.global.modules.ApplicationModule;
import ru.kavyrshin.rianews.di.global.modules.DataModule;
import ru.kavyrshin.rianews.di.global.modules.DatabaseModule;
import ru.kavyrshin.rianews.di.global.modules.NetworkModule;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, DatabaseModule.class, DataModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(RiaNewsApplication riaNewsApplication);

        ApplicationComponent build();
    }


    NewsListComponent.Builder newsListComponent();
    DetailedNewsComponent.Builder detailedNewsComponent();
}
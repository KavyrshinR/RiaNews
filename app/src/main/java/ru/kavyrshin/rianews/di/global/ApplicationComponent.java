package ru.kavyrshin.rianews.di.global;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import ru.kavyrshin.rianews.RiaNewsApplication;
import ru.kavyrshin.rianews.di.NewsListComponent;
import ru.kavyrshin.rianews.di.global.modules.ApplicationModule;
import ru.kavyrshin.rianews.di.global.modules.DataModule;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(RiaNewsApplication riaNewsApplication);

        ApplicationComponent build();
    }


    NewsListComponent.Builder newsListComponent();
}
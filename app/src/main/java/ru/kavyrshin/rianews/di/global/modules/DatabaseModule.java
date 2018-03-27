package ru.kavyrshin.rianews.di.global.modules;


import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import ru.kavyrshin.rianews.data.database.NewsDataBase;

@Module
public class DatabaseModule {

    @Reusable
    @Provides
    public NewsDataBase provideNewsDatabase() {
        return new NewsDataBase();
    }
}
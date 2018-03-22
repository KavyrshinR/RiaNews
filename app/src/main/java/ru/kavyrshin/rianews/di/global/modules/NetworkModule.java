package ru.kavyrshin.rianews.di.global.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.kavyrshin.rianews.data.network.JSoupRiaNews;


@Module
public class NetworkModule {

    @Provides
    @Singleton
    public JSoupRiaNews provideJSoupRiaNews() {
        return new JSoupRiaNews();
    }
}
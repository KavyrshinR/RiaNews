package ru.kavyrshin.rianews.di.global.modules;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.kavyrshin.rianews.RiaNewsApplication;

@Module
public class ApplicationModule {

    @Provides
    public Context provideApplicationContext(RiaNewsApplication application) {
        return application;
    }
}
package ru.kavyrshin.rianews;

import android.app.Application;

import ru.kavyrshin.rianews.di.global.ApplicationComponent;
import ru.kavyrshin.rianews.di.global.DaggerApplicationComponent;

public class RiaNewsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().application(this).build();
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            throw new NullPointerException("Application component null");
        }

        return applicationComponent;
    }
}
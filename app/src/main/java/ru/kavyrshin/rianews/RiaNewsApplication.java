package ru.kavyrshin.rianews;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.kavyrshin.rianews.di.global.ApplicationComponent;
import ru.kavyrshin.rianews.di.global.DaggerApplicationComponent;

public class RiaNewsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfig);

        applicationComponent = DaggerApplicationComponent.builder().application(this).build();
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            throw new NullPointerException("Application component null");
        }

        return applicationComponent;
    }
}
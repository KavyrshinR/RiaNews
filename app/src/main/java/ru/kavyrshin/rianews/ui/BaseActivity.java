package ru.kavyrshin.rianews.ui;

import com.arellomobile.mvp.MvpAppCompatActivity;

import ru.kavyrshin.rianews.RiaNewsApplication;

public class BaseActivity extends MvpAppCompatActivity {

    private RiaNewsApplication application;

    public RiaNewsApplication application() {
        if (application == null) {
            application = (RiaNewsApplication) getApplication();
        }

        return application;
    }
}
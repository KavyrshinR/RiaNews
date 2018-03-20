package ru.kavyrshin.rianews.presentation.views;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {

    void showError(String message);
    void showLoading();
    void hideLoading();
}
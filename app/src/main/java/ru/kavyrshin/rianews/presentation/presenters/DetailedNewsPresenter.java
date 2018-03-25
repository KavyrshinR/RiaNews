package ru.kavyrshin.rianews.presentation.presenters;


import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import ru.kavyrshin.rianews.domain.CategoriedNewsInteractor;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.presentation.views.DetailedNewsView;

@InjectViewState
public class DetailedNewsPresenter extends BasePresenter<DetailedNewsView> {

    private CategoriedNewsInteractor interactor;

    private int newsId;

    @Inject
    public DetailedNewsPresenter(CategoriedNewsInteractor interactor, int newsId) {
        this.interactor = interactor;
        this.newsId = newsId;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getDetailedNews(newsId);
    }

    public void getDetailedNews(int id) {
        interactor.getDetailedNews(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<News>() {
                @Override
                public void onSuccess(News news) {
                    getViewState().showNews(news);
                }

                @Override
                public void onError(Throwable e) {
                    getViewState().showError(e.getMessage());
                }
            });
    }
}
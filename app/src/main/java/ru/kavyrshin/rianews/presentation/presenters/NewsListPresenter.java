package ru.kavyrshin.rianews.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import ru.kavyrshin.rianews.domain.CategoriedNewsInteractor;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.presentation.views.NewsListView;

@InjectViewState
public class NewsListPresenter extends BasePresenter<NewsListView> {

    private CategoriedNewsInteractor categoriedNewsInteractor;

    @Inject
    public NewsListPresenter(CategoriedNewsInteractor categoriedNewsInteractor) {
        this.categoriedNewsInteractor = categoriedNewsInteractor;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getAllNewsCategories();
    }

    public void getAllNewsCategories() {
        unsubscribeOnDestroy(
            categoriedNewsInteractor.getAllCategories()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<Category>>() {
                        @Override
                        public void onSuccess(List<Category> categories) {
                            getViewState().showCategories(categories);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getViewState().showError(e.getMessage());
                        }
                    })
        );
    }

    public void getCategorizedNewsById(int id) {
        unsubscribeOnDestroy(
            categoriedNewsInteractor.getNewsByCategory(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<News>>() {
                    @Override
                    public void onSuccess(List<News> news) {
                        getViewState().showNews(news);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showError(e.getMessage());
                    }
                })
        );
    }
}

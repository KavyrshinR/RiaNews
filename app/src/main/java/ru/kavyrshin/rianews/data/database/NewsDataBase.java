package ru.kavyrshin.rianews.data.database;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;

public class NewsDataBase {

    public Completable saveCategories(List<Category> categories) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(categories);
        realm.commitTransaction();
        realm.close();

        return Completable.complete();
    }

    public Single<List<Category>> getCategoriesList() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Category> realmResults = realm.where(Category.class).findAll();
        List<Category> resultList = realm.copyFromRealm(realmResults);
        realm.commitTransaction();
        realm.close();

        return Single.just(resultList);
    }

    public Single<Category> getCategoryById(int categoryId) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Category temp = realm.where(Category.class).equalTo("id", categoryId).findFirst();
        Category result = realm.copyFromRealm(temp);
        realm.commitTransaction();
        realm.close();

        return Single.just(result);
    }

    public Completable saveNewsList(List<News> newsList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<News> result = realm.copyToRealmOrUpdate(newsList);
        realm.commitTransaction();
        realm.close();

        if (!result.isEmpty()) {
            return Completable.complete();
        } else {
            return Completable.error(new RuntimeException("Delete error"));
        }
    }

    public Completable deleteAllCategories() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults realmResults = realm.where(Category.class).findAll();
        boolean result = realmResults.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();

        if (result) {
            return Completable.complete();
        } else {
            return Completable.error(new RuntimeException("Delete error"));
        }
    }

    public Single<List<News>> getNewsByCategory(Category category) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<News> realmResults = realm.where(News.class).equalTo("category.id", category.getId()).findAll();
        List<News> resultList = realm.copyFromRealm(realmResults);
        realm.commitTransaction();
        realm.close();

        return Single.just(resultList);
    }

    public Completable saveNews(News news) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(news);
        realm.commitTransaction();
        realm.close();

        return Completable.complete();
    }

    public Single<News> getNewsById(int newsId) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        News temp = realm.where(News.class).equalTo("id", newsId).findFirst();
        News result = realm.copyFromRealm(temp);
        realm.commitTransaction();
        realm.close();

        return Single.just(result);
    }

    public Completable deleteAllNews() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<News> realmResults = realm.where(News.class).findAll();
        boolean result = realmResults.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();

        if (result) {
            return Completable.complete();
        } else {
            return Completable.error(new RuntimeException("Delete error"));
        }
    }

    public Completable deleteNewsByCategory(Category category) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<News> realmResults = realm.where(News.class).equalTo("category.id", category.getId()).findAll();
        boolean result = realmResults.deleteAllFromRealm();

        result = result || realmResults.isEmpty();

        realm.commitTransaction();
        realm.close();

        if (result) {
            return Completable.complete();
        } else {
            return Completable.error(new RuntimeException("Delete error"));
        }
    }
}
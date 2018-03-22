package ru.kavyrshin.rianews.domain.global.repositories;

import java.util.List;

import io.reactivex.Single;
import ru.kavyrshin.rianews.domain.global.models.Category;

public interface ICategoriesRepository {
    Single<List<Category>> getAllCategories();
    Single<Category> getCategoryById(int id);
}
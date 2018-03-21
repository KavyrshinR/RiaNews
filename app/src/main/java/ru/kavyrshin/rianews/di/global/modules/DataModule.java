package ru.kavyrshin.rianews.di.global.modules;


import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import ru.kavyrshin.rianews.data.repositories.CategoriesRepository;
import ru.kavyrshin.rianews.domain.global.repositories.ICategoriesRepository;

@Module
public class DataModule {

    @Reusable
    @Provides
    public ICategoriesRepository provideCategoriesRepository(CategoriesRepository categoriesRepository) {
        return categoriesRepository;
    }


}
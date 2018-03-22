package ru.kavyrshin.rianews.data.network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import ru.kavyrshin.rianews.domain.global.models.Category;

public class JSoupRiaNews {

    public Single<List<Category>> getAllCategories() {
        return Single.fromCallable(new Callable<List<Category>>() {
            @Override
            public List<Category> call() throws Exception {
                List<Category> categories = new ArrayList<>();

                Document riaRu = Jsoup.connect("http://ria.ru").get();
                Elements categoriesHtml = riaRu.select("ul.b-main-nav__main").first().select("li[data-point]");

                for (Element element : categoriesHtml) {
                    Category category = new Category();
                    category.setName(element.select("a[href]").first().select("span").first().text());
                    category.setUrl("http://ria.ru" + element.select("a[href]").first().attr("href"));

                    if (!category.getUrl().contains("rsport")) {
                        categories.add(category);
                    }
                }
                categories.get(0).setUrl(categories.get(0) + "lenta");

                return categories;
            }
        });
    }
}
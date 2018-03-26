package ru.kavyrshin.rianews.data.network;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import ru.kavyrshin.rianews.domain.global.models.Category;
import ru.kavyrshin.rianews.domain.global.models.News;

public class JSoupRiaNews {

    private SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault());

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
                    String url = element.select("a[href]").first().attr("href");
                    category.setUrl(url);
                    if (!url.contains("ria.ru")) {
                        category.setUrl("http://ria.ru" + url);
                    }

                    if (!category.getUrl().contains("rsport")) {
                        categories.add(category);
                    }
                }
                categories.get(0).setUrl(categories.get(0).getUrl() + "lenta");

                return categories;
            }
        });
    }

    public Single<List<News>> getNewsByCategory(final Category category) {
        return Single.fromCallable(new Callable<List<News>>() {
            @Override
            public List<News> call() throws Exception {
                List<News> newsList = new ArrayList<>();
                Document categoryHtml = Jsoup.connect(category.getUrl()).get();
                Element contentHtml = categoryHtml.selectFirst("div.b-list");
                if (contentHtml != null) {
                    Elements elements = contentHtml.select("div.b-list__item");

                    for (Element element : elements) {
                        News news = new News();
                        news.setImgUrl(element.selectFirst("img").attr("src"));
                        news.setCategory(category);
                        news.setName(element.selectFirst("span.b-list__item-title").text());
                        news.setUrl("http://ria.ru" + element.selectFirst("a[href]").attr("href"));

                        newsList.add(news);
                    }

                } else {
                    contentHtml = categoryHtml.selectFirst("div.b-themespec__tiles-list").selectFirst("div.owl-carousel");
                    Elements elements = contentHtml.select("div.b-themespec__tiles-item");

                    for (Element element : elements) {
                        News news = new News();
                        news.setImgUrl(element.selectFirst("img").attr("src"));
                        news.setCategory(category);
                        news.setName(element.selectFirst("span.b-themespec__tiles-item-title").selectFirst("span").text());
                        String url = element.selectFirst("a.b-themespec__tiles-item-link").attr("href");
                        news.setUrl(url);
                        if (!url.contains("ria.ru")) {
                            news.setUrl("http://ria.ru" + url);
                        }

                        newsList.add(news);
                    }
                }

                return newsList;
            }
        });
    }

    public Single<News> getDetailedNews(final News news) {
        return Single.fromCallable(new Callable<News>() {
            @Override
            public News call() throws Exception {
                Document newsHtml = Jsoup.connect(news.getUrl()).get();
                Elements text = newsHtml.select("div.b-article__body > p");
                news.setText(text.outerHtml());
                news.setBigImgUrl(newsHtml.selectFirst("div.l-photoview__open").selectFirst("img").attr("src"));
                String textDate = newsHtml.selectFirst("div.b-article__info-date").attr("datetime");
                Date dateTime = dateParser.parse(textDate);
                news.setUnixtime(dateTime.getTime());
                return news;
            }
        });
    }
}
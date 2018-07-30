package com.allenanker.android.simplenews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

class RetrieveNews extends AsyncTask<String, Void, List<News>> {

    @Override
    protected List<News> doInBackground(String... urls) {
        List<News> newsList = new ArrayList<>();
        String title, source, url;
        News news;
        try {
            final org.jsoup.nodes.Document newsDoc = Jsoup.connect(urls[0]).get();
            Elements newsElements = newsDoc.getElementsByAttributeValue("data-role","news-item");
            for(org.jsoup.nodes.Element element:newsElements){
                source = element.select("span[class=name]").first().text();
                title = element.select("h4").text();
                url = element.select("h4").first().selectFirst("a").attr("href");
                news = new News(title, source, source, url);
                newsList.add(news);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return newsList;
    }
}

public class NewsLab {

    private static NewsLab sNewsLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static final String POLITICS_URL = "http://www.sohu.com/c/8/1460";
    public static final String INTER_URL = "http://www.sohu.com/c/8/1461";
    public static final String FINANCE_URL = "http://www.sohu.com/c/8/1463";

    public static NewsLab get(Context context) {
        if (sNewsLab == null) {
            sNewsLab = new NewsLab(context);
        }

        return sNewsLab;
    }

    private NewsLab(Context context) {
        mContext = context;
    }

    public List<News> getNews(int type) {
        List<News> newsList;
        switch (type) {
            case 1:
                newsList = getPoliticalNews();
                break;
            case 2:
                newsList = getInternationalNews();
                break;
            case 3:
                newsList = getFinancialNews();
                break;
                default:
                    newsList = getInternationalNews();
                    break;
        }
        return newsList;
    }

    private List<News> getPoliticalNews(){
        List<News> newsList = new ArrayList<>();
        try {
            newsList =  new RetrieveNews().execute(POLITICS_URL).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    private List<News> getInternationalNews(){
        List<News> newsList = new ArrayList<>();
        try {
            newsList =  new RetrieveNews().execute(INTER_URL).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    private List<News> getFinancialNews(){
        List<News> newsList = new ArrayList<>();
        try {
            newsList =  new RetrieveNews().execute(FINANCE_URL).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}

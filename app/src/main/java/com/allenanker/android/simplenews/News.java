package com.allenanker.android.simplenews;

public class News {

    private String title;
    private String des;
    private String date;
    private String url;

    public News(String title, String des, String date, String url) {
        this.title = title;
        this.des = des;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}

package com.bitnovisad.ndexample1.news_list.model;

public class NewsItem implements IntNewsItem{

    private String urlToImage;
    private String title;
    private String description;
    private String content;
    private String author;
    private String publishedDate;
    //private String url;


    public NewsItem(String urlToImage, String title, String publishedDate) {
        this.urlToImage = urlToImage;
        this.title = title;
        this.publishedDate = publishedDate;
    }

    public NewsItem(String urlToImage, String title, String description, String content, String author, String publishedDate) {
        this.urlToImage = urlToImage;
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
        this.publishedDate = publishedDate;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }
}

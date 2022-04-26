package com.example.astrodaily;

public class Article {
    private String title, author, publishDate, link, sourceUrl, excerpt, summary, picUrl, isOpinion;

    public Article(String title, String author, String publishDate, String link, String sourceUrl, String excerpt, String summary, String picUrl, String isOpinion) {
        this.title = title;
        this.author = author;
        this.publishDate= publishDate;
        this.link = link;
        this.sourceUrl = sourceUrl;
        this.excerpt = excerpt;
        this.summary = summary;
        this.picUrl = picUrl;
        this.isOpinion = isOpinion;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getLink() {
        return link;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getSummary() {
        return summary;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getIsOpinion() {
        return isOpinion;
    }
}

package com.main;


class VideoEntry {
    private String title;
    private String link;
    private String publishedDate;
    private String author;

    public VideoEntry(String title, String link, String publishedDate, String author) {
        this.title = title;
        this.link = link;
        this.publishedDate = publishedDate;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nLink: " + link + "\nPublished: " + publishedDate + "\nAuthor: " + author + "\n";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
}
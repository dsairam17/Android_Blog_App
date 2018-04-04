package com.example.dsair.techfortechie;

public class Post {
    private int id;
    private String title;
    private String content;
    private String permalink;
    private String imageLoc;
    private String author;

    public Post(int id, String title, String content, String permalink, String imageLoc, String author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.permalink = permalink;
        this.imageLoc = imageLoc;
        this.author = author;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getImageLoc() {
        return imageLoc;
    }

    public String getAuthor() {
        return author;
    }

}

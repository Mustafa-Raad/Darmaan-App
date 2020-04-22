package com.uth.raad.darmaan.news_section;

/**
 * Created by AssassinM on 7/2/2017.
 */

public class Blog {
    private String title;
    private String description;
    private String image;

    public Blog(){

    }

    public Blog(String description, String image, String title) {
        this.description = description;
        this.image = image;
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String dsc) {
        this.description = dsc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

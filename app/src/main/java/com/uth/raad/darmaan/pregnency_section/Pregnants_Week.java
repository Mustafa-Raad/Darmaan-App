package com.uth.raad.darmaan.pregnency_section;

/**
 * Created by raad on 5/10/2018.
 */

public class Pregnants_Week {
    private String Title;
    private String Category;
    private String Description;
    private int Thumbnail;

    public Pregnants_Week(){

    }

    public Pregnants_Week(String weekTitle, String category, String description, int thumbnail) {
        Title = weekTitle;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
    }
// getter medhods
    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

// setter mehods

    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}

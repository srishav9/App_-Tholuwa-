package com.example.hp.dc_project;

public class workers {
    String name,image;

    public workers(String name,String image) {
        this.image = image;
        this.name = name;
    }

    public workers() {
    }

    public String getTitle() {return name;   }
    public void setTitle(String title) {
        this.name = title;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
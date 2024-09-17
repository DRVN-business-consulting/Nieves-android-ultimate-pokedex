package com.nieves.nieves_pokedex.model;

public class Pokemon {

    private int id;
    private String name;
    private String type;
    private String description;
    private int imageResId;
    private int imageLogo;

    public Pokemon(int id, String name, String type, String description, int imageResId,int imageLogo) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.imageLogo = imageLogo;
        this.imageResId = imageResId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(int imageLogo) {
        this.imageLogo = imageLogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}

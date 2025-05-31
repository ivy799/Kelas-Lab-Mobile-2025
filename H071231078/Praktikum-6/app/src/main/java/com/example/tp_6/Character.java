package com.example.tp_6;

public class Character {
    private int id;
    private String image;
    private String name;
    private String species;
    private String status;
    private String gender;

    public Character(int id, String image, String name, String species, String status, String gender) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.species = species;
        this.status = status;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

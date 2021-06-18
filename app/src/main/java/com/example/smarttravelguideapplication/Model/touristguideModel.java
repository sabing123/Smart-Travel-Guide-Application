package com.example.smarttravelguideapplication.Model;


public class touristguideModel {
    private String name;
    private String address;
    private String contact;
    private String language;
    private String description;
    private String image;

    public touristguideModel(String name, String address, String contact, String language, String description, String image) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.language = language;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

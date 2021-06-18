package com.example.smarttravelguideapplication.Model;

public class HotelModel {
    private String name;
    private String location;
    private String price;
    private String description;
    private String image;
    private String contactnumber;

    public HotelModel(String name, String location, String price, String description, String image, String contactnumber) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.description = description;
        this.image = image;
        this.contactnumber = contactnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }
}

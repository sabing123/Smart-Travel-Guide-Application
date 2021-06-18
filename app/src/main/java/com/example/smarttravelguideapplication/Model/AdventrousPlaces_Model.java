package com.example.smarttravelguideapplication.Model;

public class AdventrousPlaces_Model {

    public String name;
    public String location;
    public String description;
    public String price;
    public String BestTimeToVisit;
    public String image;

    public AdventrousPlaces_Model(String name, String location, String description, String price, String bestTimeToVisit, String image) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
        BestTimeToVisit = bestTimeToVisit;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBestTimeToVisit() {
        return BestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        BestTimeToVisit = bestTimeToVisit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

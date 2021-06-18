package com.example.smarttravelguideapplication.Model;

public class HistoricalPlaces {
    public String name;
    public String location;
    public String description;
    public String timing;
    public String EntryFree;
    public String image;
    public String lat, lon;

    public HistoricalPlaces(String name, String location, String description, String timing, String entryFree, String image, String lat, String lon) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.timing = timing;
        this.EntryFree = entryFree;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
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

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getEntryFree() {
        return EntryFree;
    }

    public void setEntryFree(String entryFree) {
        EntryFree = entryFree;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}

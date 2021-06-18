package com.example.smarttravelguideapplication.Model;

public class cabinfoMOdel {

    public String  driverimage;
    public String  name;
    public String contactnumber;
    public String price;
    public String description;
    public String  cabname;
    public String  cabmodel;
    public String cabImage;
    public String  cabdescription;

    public cabinfoMOdel(String driverimage, String name, String contactnumber, String price, String description, String cabname, String cabmodel, String cabImage, String cabdescription) {
        this.driverimage = driverimage;
        this.name = name;
        this.contactnumber = contactnumber;
        this.price = price;
        this.description = description;
        this.cabname = cabname;
        this.cabmodel = cabmodel;
        this.cabImage = cabImage;
        this.cabdescription = cabdescription;
    }

    public String getDriverimage() {
        return driverimage;
    }

    public void setDriverimage(String driverimage) {
        this.driverimage = driverimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
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

    public String getCabname() {
        return cabname;
    }

    public void setCabname(String cabname) {
        this.cabname = cabname;
    }

    public String getCabImage() {
        return cabImage;
    }

    public void setCabImage(String cabImage) {
        this.cabImage = cabImage;
    }

    public String getCabdescription() {
        return cabdescription;
    }

    public void setCabdescription(String cabdescription) {
        this.cabdescription = cabdescription;
    }

    public String getCabmodel() {
        return cabmodel;
    }

    public void setCabmodel(String cabmodel) {
        this.cabmodel = cabmodel;
    }
}

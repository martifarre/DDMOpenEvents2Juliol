package com.example.ddmopenevents2.business;

public class EventResponse {
    private String name;
    private String image;
    private String location;
    private String description;
    private String eventStart_date;
    private String eventEnd_date;
    private int n_participators;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getEventStart_date() {
        return eventStart_date;
    }

    public void setEventStart_date(String eventStart_date) {
        this.eventStart_date = eventStart_date;
    }

    public String getEventEnd_date() {
        return eventEnd_date;
    }

    public void setEventEnd_date(String eventEnd_date) {
        this.eventEnd_date = eventEnd_date;
    }

    public int getN_participators() {
        return n_participators;
    }

    public void setN_participators(int n_participators) {
        this.n_participators = n_participators;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

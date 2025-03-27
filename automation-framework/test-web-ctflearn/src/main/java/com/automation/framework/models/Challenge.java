package com.automation.framework.models;

public class Challenge {
    private String event;
    private String title;
    private String flag;
    private String description;
    private String file;
    private String category;
    private String points;
    private String howToSolve;

    public Challenge() {
    }

    public Challenge(String event, String title, String flag, String description, String file, String category,
            String points, String howToSolve) {
        this.event = event;
        this.title = title;
        this.flag = flag;
        this.description = description;
        this.file = file;
        this.category = category;
        this.points = points;
        this.howToSolve = howToSolve;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getHowToSolve() {
        return howToSolve;
    }

    public void setHowToSolve(String howToSolve) {
        this.howToSolve = howToSolve;
    }
}

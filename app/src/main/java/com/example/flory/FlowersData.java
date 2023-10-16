package com.example.flory;

public class FlowersData {

    private String flowerTitle;
    private String flowerScientificalName;
    private String flowerDescription;
    private String flowerPhoto;
    private String key;


    public FlowersData() {
        // Default constructor required for calls to DataSnapshot.getValue(DiseasesData.class)
    }

    public FlowersData(String flowerTitle, String flowerScientificalName, String flowerDescription, String flowerPhoto) {
        this.flowerTitle = flowerTitle;
        this.flowerScientificalName = flowerScientificalName;
        this.flowerDescription = flowerDescription;
        this.flowerPhoto = flowerPhoto;

    }

    public String getFlowerTitle() {
        return flowerTitle;
    }

    public void setFlowerTitle(String flowerTitle) {
        this.flowerTitle = flowerTitle;
    }

    public String getFlowerScientificalName() {
        return flowerScientificalName;
    }

    public void setFlowerScientificalName(String flowerScientificalName) {
        this.flowerScientificalName = flowerScientificalName;
    }

    public String getFlowerDescription() {
        return flowerDescription;
    }

    public void setFlowerDescription(String flowerDescription) {
        this.flowerDescription = flowerDescription;
    }

    public String getFlowerPhoto() {
        return flowerPhoto;
    }

    public void setFlowerPhoto(String flowerPhoto) {
        this.flowerPhoto = flowerPhoto;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

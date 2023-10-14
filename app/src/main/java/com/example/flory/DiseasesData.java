package com.example.flory;

public class DiseasesData {
    private String diseaseTitle;
    private String diseaseDescription;
    private String diseaseTreeatement;
    private String diseasePhoto;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getDiseaseTitle() {
        return diseaseTitle;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public String getDiseaseTreeatement() {
        return diseaseTreeatement;
    }

    public String getDiseasePhoto() {
        return diseasePhoto;
    }

    public DiseasesData(String diseaseTitle, String diseaseDescription, String diseaseTreeatement, String diseasePhoto) {
        this.diseaseTitle = diseaseTitle;
        this.diseaseDescription = diseaseDescription;
        this.diseaseTreeatement = diseaseTreeatement;
        this.diseasePhoto = diseasePhoto;
    }

    public DiseasesData() {
        // Default constructor required for calls to DataSnapshot.getValue(DiseasesData.class)
    }
}

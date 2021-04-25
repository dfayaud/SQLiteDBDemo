package com.example.organizingapp;

public class Notebook {
    private int notebookId;
    private String notebookName;
    private String notebookColor;
    private String pictureLocation;
    private boolean isList;



    public Notebook(int notebookId, String notebookName, String notebookColor, String pictureLocation, boolean isList) {
        this.notebookId = notebookId;
        this.notebookName = notebookName;
        this.notebookColor = notebookColor;
        this.pictureLocation = pictureLocation;
        this.isList = isList;
    }

    public Notebook() {
    }

    public int getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public String getNotebookColor() {
        return notebookColor;
    }

    public void setNotebookColor(String notebookColor) {
        this.notebookColor = notebookColor;
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "notebookId=" + notebookId +
                ", notebookName='" + notebookName + '\'' +
                ", notebookColor='" + notebookColor + '\'' +
                ", pictureLocation='" + pictureLocation + '\'' +
                ", isList=" + isList +
                '}';
    }
}


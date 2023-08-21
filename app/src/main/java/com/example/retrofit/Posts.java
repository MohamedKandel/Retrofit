package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Posts {
    @SerializedName("id")
    private Integer ID;

    @SerializedName("userId")
    private int user_ID;

    @SerializedName("body")     //because this called "body" in json we used this annotation
    private String text;

    private String title;


    public Posts(int user_ID, String title, String text) {
        this.user_ID = user_ID;
        this.text = text;
        this.title = title;
    }

    public Posts(int user_ID, String text) {
        this.user_ID = user_ID;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

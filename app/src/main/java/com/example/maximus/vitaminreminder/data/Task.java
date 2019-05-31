package com.example.maximus.vitaminreminder.data;

public class Task {

    private String mId;

    private String mTitle;
    //????
    private String mTime;

    private boolean mCompleted;


    public Task() {

    }

    public Task(String title, String time) {
        this.mTitle = title;
        mCompleted = false;
        this.mTime = time;
    }

    public Task(String id, String title, String time) {
        this.mTitle = title;
        mCompleted = false;
        this.mTime = time;
        this.mId = id;
    }



    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public boolean isEmpty() {
        return mTitle.isEmpty() && mTime.isEmpty();
    }
}

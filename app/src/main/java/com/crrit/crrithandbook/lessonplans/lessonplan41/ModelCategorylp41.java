package com.crrit.crrithandbook.lessonplans.lessonplan41;

public class ModelCategorylp41 {
    String id,category,uid;
    long timestamp;

    //empty constructor for FB


    public ModelCategorylp41() {
    }

    //parametrized constructor

    public ModelCategorylp41(String id, String category, String uid, long timestamp) {
        this.id = id;
        this.category = category;
        this.uid = uid;
        this.timestamp = timestamp;
    }
    //getters/setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

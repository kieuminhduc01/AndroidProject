package com.example.appexcerse.model;

public class TrainningInfo {
    private long userId;
    private float height;
    private float weight;
    private int maxRepPushUp;
    private int maxRepPullUp;
    private int maxRepSquads;
    private MucDichTap mucDichTap;
    private String level;

    public TrainningInfo() {
    }

    public TrainningInfo(float height, float weight, int maxRepPushUp, int maxRepPullUp, int maxRepSquads, MucDichTap mucDichTap, String level) {
        this.height = height;
        this.weight = weight;
        this.maxRepPushUp = maxRepPushUp;
        this.maxRepPullUp = maxRepPullUp;
        this.maxRepSquads = maxRepSquads;
        this.mucDichTap = mucDichTap;
        this.level = level;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getMaxRepPushUp() {
        return maxRepPushUp;
    }

    public void setMaxRepPushUp(int maxRepPushUp) {
        this.maxRepPushUp = maxRepPushUp;
    }

    public int getMaxRepPullUp() {
        return maxRepPullUp;
    }

    public void setMaxRepPullUp(int maxRepPullUp) {
        this.maxRepPullUp = maxRepPullUp;
    }

    public int getMaxRepSquads() {
        return maxRepSquads;
    }

    public void setMaxRepSquads(int maxRepSquads) {
        this.maxRepSquads = maxRepSquads;
    }

    public MucDichTap getMucDichTap() {
        return mucDichTap;
    }

    public void setMucDichTap(MucDichTap mucDichTap) {
        this.mucDichTap = mucDichTap;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

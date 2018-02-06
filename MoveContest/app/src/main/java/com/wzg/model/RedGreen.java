package com.wzg.model;

/**
 * Created by WangZhiGang on 2018/2/6.
 */

public class RedGreen {

    private int intersection;
    private int red;
    private int yellow;
    private int green;
    private boolean check;


    public int getIntersection() {
        return intersection;
    }

    public void setIntersection(int intersection) {
        this.intersection = intersection;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    public RedGreen(int intersection, int red, int yellow, int green) {
        this.intersection = intersection;
        this.red = red;
        this.yellow = yellow;
        this.green = green;
    }
}

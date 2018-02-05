package com.wzg.model;

/**
 * Created by WangZhiGang on 2018/2/4.
 */

public class CarNumber {

    private String carId;
    private String time;
    private String distance;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }


    public CarNumber(String carId, String time, String distance) {
        this.carId = carId;
        this.time = time;
        this.distance = distance;
    }
}

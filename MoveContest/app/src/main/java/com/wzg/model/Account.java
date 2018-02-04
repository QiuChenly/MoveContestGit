package com.wzg.model;

/**
 * Created by WangZhiGang on 2018/2/2.
 */

public class Account {


    private String carId;
    private int carImg;
    private String carNumber;
    private String carMaster;
    private String balance;
    private boolean checkState;

    public Account(String carId, int carImg, String carNumber, String carMaster, String balance) {
        this.carId = carId;
        this.carImg = carImg;
        this.carNumber = carNumber;
        this.carMaster = carMaster;
        this.balance = balance;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public int getCarImg() {
        return carImg;
    }

    public void setCarImg(int carImg) {
        this.carImg = carImg;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarMaster() {
        return carMaster;
    }

    public void setCarName(String carMaster) {
        this.carMaster = carMaster;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public boolean isCheckState() {
        return checkState;
    }

    public void setCheckState(boolean checkState) {
        this.checkState = checkState;
    }
}

package com.wzg.model;

/**
 * Created by WangZhiGang on 2018/2/5.
 */

public class Bus {

    private int id;
    private int busId;
    private int people;


    public Bus(int id, int busId, int people) {
        this.id = id;
        this.busId = busId;
        this.people = people;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}

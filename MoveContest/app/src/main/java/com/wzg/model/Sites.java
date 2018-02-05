package com.wzg.model;

import java.util.List;

/**
 * Created by WangZhiGang on 2018/2/4.
 */

public class Sites {

    private String siteName;
    private List<CarNumber> mCarNumberList;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public List<CarNumber> getCarNumberList() {
        return mCarNumberList;
    }

    public void setCarNumberList(List<CarNumber> carNumberList) {
        mCarNumberList = carNumberList;
    }

    public Sites(String siteName, List<CarNumber> carNumberList) {
        this.siteName = siteName;
        mCarNumberList = carNumberList;
    }
}

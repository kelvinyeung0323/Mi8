package com.hidata.mi8pro.Index;

import java.io.Serializable;

/**
 * Created by k_way on 2017/5/19.
 */

public class IndexSummary implements Serializable{

    private String nowIndex;
    private String updown;
    private String rate;

    public String getNowIndex() {
        return nowIndex;
    }

    public void setNowIndex(String nowIndex) {
        this.nowIndex = nowIndex;
    }

    public String getUpdown() {
        return updown;
    }

    public void setUpdown(String updown) {
        this.updown = updown;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}

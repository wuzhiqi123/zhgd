package com.zhgd.controller.controllerUtile.parma;

import java.util.List;

public class ZpParma {
    private String rynm;
    private String zp;
    private String sjsj;
    private String nm;
    private List<String> rynmList;

    public List<String> getRynmList() {
        return rynmList;
    }

    public void setRynmList(List<String> rynmList) {
        this.rynmList = rynmList;
    }

    public String getRynm() {
        return rynm;
    }

    public void setRynm(String rynm) {
        this.rynm = rynm;
    }

    public String getZp() {
        return zp;
    }

    public void setZp(String zp) {
        this.zp = zp;
    }

    public String getSjsj() {
        return sjsj;
    }

    public void setSjsj(String sjsj) {
        this.sjsj = sjsj;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }
}

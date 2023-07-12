package com.example.managesystem.activity;

public class activity {

    public activity(){

    }
    public String AID;
    public String ANAME;
    public String CID;
    public String ACONTENT;
    public String BEGINTIME;
    public String ENDTIME;

    public String STATUS;

    public boolean type;


    public void setType(boolean type) {
        this.type = type;
    }

    public boolean getType() {
        return type;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCID() {
        return CID;
    }

    public String getACONTENT() {
        return ACONTENT;
    }

    public String getAID() {
        return AID;
    }

    public String getANAME() {
        return ANAME;
    }

    public String getBEGINTIME() {
        return BEGINTIME;
    }

    public String getENDTIME() {
        return ENDTIME;
    }

    public void setACONTENT(String ACONTENT) {
        this.ACONTENT = ACONTENT;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public void setANAME(String ANAME) {
        this.ANAME = ANAME;
    }

    public void setBEGINTIME(String BEGINTIME) {
        this.BEGINTIME = BEGINTIME;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public void setENDTIME(String ENDTIME) {
        this.ENDTIME = ENDTIME;
    }
}

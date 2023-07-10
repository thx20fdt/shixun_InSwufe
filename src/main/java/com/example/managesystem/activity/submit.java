package com.example.managesystem.activity;

import java.util.Date;

public class submit {
    public String SID;
    public String AID;
    public String GID;
    public String SNAME;
    public String CONTENT;
    public Date SUBMITTIME;
    public String ANAME;
    public String ACONTENT;

    public double SCORE;
    public double AVGSCORE;

    public double getAVGSCORE() {
        return AVGSCORE;
    }

    public void setAVGSCORE(double AVGSCORE) {
        this.AVGSCORE = AVGSCORE;
    }

    public void setSCORE(double SCORE) {
        this.SCORE = SCORE;
    }

    public double getSCORE() {
        return SCORE;
    }

    public String getAID() {
        return AID;
    }

    public String getACONTENT() {
        return ACONTENT;
    }

    public void setACONTENT(String ACONTENT) {
        this.ACONTENT = ACONTENT;
    }

    public Date getSUBMITTIME() {
        return SUBMITTIME;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public String getGID() {
        return GID;
    }

    public String getSID() {
        return SID;
    }

    public void setANAME(String ANAME) {
        this.ANAME = ANAME;
    }

    public String getANAME() {
        return ANAME;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public void setSUBMITTIME(Date SUBMITTIME) {
        this.SUBMITTIME = SUBMITTIME;
    }

}

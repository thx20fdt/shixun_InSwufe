package com.example.managesystem.score;

public class Score {
    private String CNAME;
    private String ANAME;
    private double SCORE;

    private String AID;


    public String getCNAME() {
        return CNAME;
    }

    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }

    public String getANAME() {
        return ANAME;
    }

    public void setANAME(String ANAME) {
        this.ANAME = ANAME;
    }

    public double getSCORE() {
        return SCORE;
    }

    public void setSCORE(double SCORE) {
        this.SCORE = SCORE;
    }

    public String getAID() {
        return AID;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }
}

package com.example.managesystem.user;

public class Teacher {
    public String TID;
    public String NAME;
    public String PHONE;
    public boolean GENDER;

    public String getTID() {
        return TID;
    }

    public boolean getGENDER() {
        return GENDER;
    }

    public String getPHONE() {
        return PHONE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public void setGENDER(boolean GENDER) {
        this.GENDER = GENDER;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
}

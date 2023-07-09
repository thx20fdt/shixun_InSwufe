package com.example.managesystem.group;

import java.util.List;

public class Group { private String CNAME;
    private String ANAME;
    private String GID;

    private String AID;
    private List<String> MEMBERS;


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

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getAID() {
        return AID;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }


    public List<String> getMEMBERS() {
        return MEMBERS;
    }

    public void setMembers(List<String> MEMBERS) {
        this.MEMBERS = MEMBERS;
    }
}

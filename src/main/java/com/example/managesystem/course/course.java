package com.example.managesystem.course;

public class course {
    public String CID;
    public String CNAME;
    public String TID;
    public String TNAME;

    public String CLASSTIME;

    public course() {

    }

    public String getCID() {
        return CID;
    }
    public String getCNAME(){
        return CNAME;
    }

    public String getTID() {
        return TID;
    }

    public void setCID(String CID){
        this.CID = CID;
    }
    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }
    public void setTID(String TID) {
        this.TID = TID;
    }

    public void setTNAME(String TNAME) {
        this.TNAME = TNAME;
    }

    public void setCLASSTIME(String CLASSTIME){
        this.CLASSTIME = CLASSTIME;
    }
}

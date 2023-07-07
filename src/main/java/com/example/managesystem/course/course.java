package com.example.managesystem.course;

public class course {
    public String CID;
    public String CNAME;
    public String TID;
    public String TNAME;

    public course(){

    };
    public course(String CID,String CNAME,String TID){
        this.CID = CID;
        this.CNAME = CNAME;
        this.TID = TID;
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
}

package com.tj.beans;

/**
 * Created by LiuFangGuo on 10/13/15.
 */
public class ErrorsGrep {
    //------grep fields-----
    private String did;
    private String record_time;
    private String error_info;

    @Override
    public String toString() {
        return did + "," + record_time + "," + error_info;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }
}

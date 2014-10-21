package net.wicp.yunjigroup.oa.models;

import java.io.Serializable;

public class BaseData implements Serializable{

    private static final long serialVersionUID = 19345734L;
    
    
    public static final String KEY_DATA = "data";
    public static final String KEY_ERROR = "error";
    public static final String KEY_ERRORCODE = "errorcode";
    public static final String KEY_ERRORDESC = "errordesc";
    
    protected String error = "";
    protected String errorcode = "";
    protected String errordesc = "";

    public String getError() {
        return error;
    }

    public void setError( String error ) {
        this.error = error;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode( String errorcode ) {
        this.errorcode = errorcode;
    }

    public String getErrordesc() {
        return errordesc;
    }

    public void setErrordesc( String errordesc ) {
        this.errordesc = errordesc;
    }
    
    
}

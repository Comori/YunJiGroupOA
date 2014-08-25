package net.wicp.yunjigroup.oa.models;

import java.io.Serializable;

public class BaseData implements Serializable{

    private static final long serialVersionUID = 19345734L;
    
    public static final String KEY_ERROR = "error";
    
    protected String error = "";

    public String getError() {
        return error;
    }

    public void setError( String error ) {
        this.error = error;
    }
    

}

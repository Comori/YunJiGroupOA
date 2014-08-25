package net.wicp.yunjigroup.oa.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Base64;

public class Utils {
    
    public static String mapToJsonStr(Map<String, Object> map){
        if(map == null){
            return null;
        }
        JSONObject json = new JSONObject();
        Iterator<String> keys = map.keySet().iterator();
        try {
        while(keys.hasNext()){
            String key = keys.next();
            Object value = map.get(key);
            json.put(key, value);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    
    public static String encrypt(String str){
        String result = null;
        if(!TextUtils.isEmpty(str)){
            try {
                result = URLEncoder.encode(str, "UTF-8");
                result = Base64.encodeToString(result.getBytes(), Base64.DEFAULT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static String decrypt(String str){
        String result = null;
        if(!TextUtils.isEmpty(str)){
            try {
                result = new String(Base64.decode(str, Base64.DEFAULT), "UTF-8");
                result = URLDecoder.decode(result, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

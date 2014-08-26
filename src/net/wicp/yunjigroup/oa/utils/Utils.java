package net.wicp.yunjigroup.oa.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;

public class Utils {

    public static String mapToJsonStr( Map<String, Object> map ) {
        if (map == null) {
            return null;
        }
        JSONObject json = new JSONObject();
        Iterator<String> keys = map.keySet().iterator();
        try {
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = map.get(key);
                json.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String encrypt( String str ) {
        String result = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                result = URLEncoder.encode(str, "UTF-8");
                result = Base64.encodeToString(result.getBytes(), Base64.DEFAULT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String decrypt( String str ) {
        String result = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                result = new String(Base64.decode(str, Base64.DEFAULT), "UTF-8");
                result = URLDecoder.decode(result, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void writeStringToSP( Context context, String key, String value ) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String readStringFromSP( Context context, String key ) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }

    public static void saveObjToSP( Context context, String key, Object value ) throws IOException {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(value);
        String objString = new String(Base64.encode(byteArrayOutputStream.toByteArray(),
                Base64.DEFAULT));
        sp.edit().putString(key, objString).commit();
        objectOutputStream.close();
    }

    public static Object getObjFromSP( Context context, String key )
            throws StreamCorruptedException, IOException, ClassNotFoundException {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String str = sp.getString(key, "");
        if (str.length() <= 0)
            return null;
        Object obj = null;
        byte[] mobileBytes = Base64.decode(str.toString().getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream;
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        obj = objectInputStream.readObject();
        objectInputStream.close();
        return obj;
    }
    
    public static boolean isLogin(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(Contants.SP_IS_LOGIN, false);
    }
    
    public static void setLoginState(Context context,boolean isLogin){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean(Contants.SP_IS_LOGIN, isLogin);
        editor.commit();
    }
}

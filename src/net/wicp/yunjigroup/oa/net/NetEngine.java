package net.wicp.yunjigroup.oa.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import net.wicp.yunjigroup.oa.models.HomeData;
import net.wicp.yunjigroup.oa.models.User;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class NetEngine {
    public static final int REQUEST_TIME_OUT = 20 * 1000 ;
    
    /**
     * post 请求
     * @param url
     * @param request
     * @return
     */
    public static Response post(String url,String request){
        HttpPost post = new HttpPost(url);
        HttpParams httpParams = new BasicHttpParams();
        HttpResponse response = null;
        
        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIME_OUT);
        HttpConnectionParams.setSoTimeout(httpParams, REQUEST_TIME_OUT);
        
        HttpClient client = new DefaultHttpClient(httpParams);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(Request.KEY_REQUEST, request));
        try {
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            response = client.execute(post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.bulid(response);
    }
    
    /**
     * 登陆
     * @param name
     * @param passwd
     * @return
     */
    public static User login(String name,String passwd){
        
        Response response = post(Request.Login.URL, Request.Login.createRequest(name, passwd));
        User user = null;
        if(response == null){
        	return null;
        }
        if(response.statusCode == HttpStatus.SC_OK){
            if(!TextUtils.isEmpty(response.content)){
                try {
                    JSONObject jsonObject = new JSONObject(response.content);
                    user = User.fromJson(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return user;
    }
    
    /**
     * 首页数据
     * @param token
     * @return
     */
    public static HomeData getHomeData(String token){
        Response response = post(Request.HomeList.URL, Request.HomeList.createRequest(token));
        HomeData homeData = null;
        if(response == null){
            return null;
        }
        if(response.statusCode == HttpStatus.SC_OK){
            if(!TextUtils.isEmpty(response.content)){
                try {
                    JSONObject jsonObject = new JSONObject(response.content);
                    homeData = HomeData.fromJson(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return homeData;
    }
    
    public static void getAddressList(String token){
        Response response = post(Request.AddressList.URL, Request.AddressList.createRequest(token));
        String s = null;
    }
    
    public static void getAddressListUser(String token,String department,String name){
        Response response = post(Request.AddressListUser.URL, Request.AddressListUser.createRequest(token,"集团领导",""));
        String s = null;
    }
    
    

}

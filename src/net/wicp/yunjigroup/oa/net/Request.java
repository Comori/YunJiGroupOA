package net.wicp.yunjigroup.oa.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.wicp.yunjigroup.oa.utils.Utils;
/**
 * 请求类，包含所有接口请求
 * @author chenqiang5
 *
 */
public class Request implements Serializable{

    private static final long serialVersionUID = 126485236L;
    
    public static final String HOST = "http://yunjigroup.wicp.net";
    
    public static final String KEY_ROUND_STR = "dd7576aecadd630b6a329799736b2b25";
    public static final String VALUE_ROUND_STR = "roundstr";
    public static final String KEY_REQUEST = "request";
    
    public static Map<String, Object> buildBaseRequest(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_ROUND_STR, VALUE_ROUND_STR);
        return map;
    }
    
    /**
     * 登陆请求
     * @author chenqiang5
     *
     */
    public static class Login{
        
        private static final String KEY_NAME = "xingming";
        private static final String KEY_PASSWD = "mima";
        
        public static final String URL = HOST + "/app/logined.php";
        
        public static String createRequest(String name,String passwd){
            Map<String, Object> params = buildBaseRequest();
            params.put(KEY_NAME, name);
            params.put(KEY_PASSWD, passwd);
            String request = Utils.mapToJsonStr(params);
            return Utils.encrypt(request);
        }
        
    }
    
    /**
     * 首页请求
     * @author chenqiang5
     *
     */
    public static class HomeList{
        
        private static final String KEY_TOKEN = "token";
        
        public static final String URL = HOST + "/app/index.php";
        
        public static String createRequest(String token){
            Map<String, Object> params = buildBaseRequest();
            params.put(KEY_TOKEN, token);
            String request = Utils.mapToJsonStr(params);
            return Utils.encrypt(request);
        }
        
    }
    /**
     * 通讯录部门列表
     * @author chenqiang5
     *
     */
    public static class AddressList{
        
        private static final String KEY_TOKEN = "token";
        
        public static final String URL = HOST + "/app/address/addresslist.php";
        
        public static String createRequest(String token){
            Map<String, Object> params = buildBaseRequest();
            params.put(KEY_TOKEN, token);
            String request = Utils.mapToJsonStr(params);
            return Utils.encrypt(request);
        }
        
    }
    
    /**
     * 通讯录部门列表
     * @author chenqiang5
     *
     */
    public static class AddressListUser{
        
        private static final String KEY_TOKEN = "token";
        private static final String KEY_DEPARTMENT = "department";
        private static final String KEY_NAME = "xingming";
        
        public static final String URL = HOST + "/app/address/addresslistuser.php";
        
        public static String createRequest(String token,String department,String name){
            Map<String, Object> params = buildBaseRequest();
            params.put(KEY_TOKEN, token);
            params.put(KEY_DEPARTMENT, department);
            params.put(KEY_NAME, name);
            String request = Utils.mapToJsonStr(params);
            return Utils.encrypt(request);
        }
        
    }

}

package net.wicp.yunjigroup.oa.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.wicp.yunjigroup.oa.utils.Utils;
/**
 * �����࣬�������нӿ�����
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
     * ��½����
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
     * ��ҳ����
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

}

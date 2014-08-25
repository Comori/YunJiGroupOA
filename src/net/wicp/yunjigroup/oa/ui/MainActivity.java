package net.wicp.yunjigroup.oa.ui;

import net.wicp.yunjigroup.oa.R;
import net.wicp.yunjigroup.oa.models.User;
import net.wicp.yunjigroup.oa.net.NetEngine;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
//        findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick( View arg0 ) {
//                new LoginTask().execute();
//            }
//        });
        
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class LoginTask extends AsyncTask<Void, Void, User>{

        @Override
        protected User doInBackground( Void... arg0 ) {
            
            User user = NetEngine.login("huangping", "123hh4");
            
            return user;
        }
        
        @Override
        protected void onPostExecute( User result ) {
            super.onPostExecute(result);
        }
        
    }

}

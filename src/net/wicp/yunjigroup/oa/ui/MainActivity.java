package net.wicp.yunjigroup.oa.ui;

import net.wicp.yunjigroup.oa.R;
import net.wicp.yunjigroup.oa.models.User;
import net.wicp.yunjigroup.oa.net.NetEngine;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private TextView nameTx = null;
	private TextView jobTx  = null;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
        nameTx = (TextView) findViewById(R.id.user_name);
        jobTx = (TextView) findViewById(R.id.user_job);
        
        new LoginTask().execute();
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
            
            User user = NetEngine.login("huangping", "1234");
            
            return user;
        }
        
        @Override
        protected void onPostExecute( User result ) {
            super.onPostExecute(result);
            if(result != null && (TextUtils.isEmpty(result.getError()) || result.getError().equalsIgnoreCase("null"))){
            	nameTx.setText(result.getName());
            	jobTx.setText(result.getDuty());
            }
        }
        
    }

}

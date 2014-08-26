package net.wicp.yunjigroup.oa.ui;

import java.io.IOException;
import java.io.StreamCorruptedException;

import net.wicp.yunjigroup.oa.R;
import net.wicp.yunjigroup.oa.models.User;
import net.wicp.yunjigroup.oa.utils.Contants;
import net.wicp.yunjigroup.oa.utils.Utils;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class HomeActivity extends BaseActivity {

	private TextView nameTx = null;
	private TextView jobTx  = null;
	private User mUser = null;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
        
        Intent intent = getIntent();
        if(intent.getSerializableExtra(Contants.SP_USER) != null){
            mUser = (User) intent.getSerializableExtra(Contants.SP_USER);
        }else{
            try {
                mUser = (User) Utils.getObjFromSP(this, Contants.SP_USER);
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    @Override
    public void initViews() {
        nameTx = (TextView) findViewById(R.id.user_name);
        jobTx = (TextView) findViewById(R.id.user_job);
        nameTx.setText(mUser.getName());
        jobTx.setText(mUser.getDuty());
    }

}

package net.wicp.yunjigroup.oa.ui;

import java.io.IOException;
import java.io.StreamCorruptedException;

import net.wicp.yunjigroup.oa.R;
import net.wicp.yunjigroup.oa.models.HomeData;
import net.wicp.yunjigroup.oa.models.User;
import net.wicp.yunjigroup.oa.net.NetEngine;
import net.wicp.yunjigroup.oa.utils.Contants;
import net.wicp.yunjigroup.oa.utils.Utils;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends BaseActivity implements OnClickListener {

	private TextView nameTx = null;
	private TextView jobTx  = null;
	private User mUser = null;
	private TextView noticeTx,mailTx,meetingTx,workFlowTx,contactTx;
	private RelativeLayout contactLayout;
	
	class GetHomeDataTask extends AsyncTask<String, Void, HomeData>{
	    
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	    }

        @Override
        protected HomeData doInBackground( String... arg0 ) {
            
            return NetEngine.getInstance(mContext).getHomeData(arg0[0]);
        }
        
        @Override
        protected void onPostExecute( HomeData result ) {
            super.onPostExecute(result);
            updateHomeData(result);
        }
	    
	}
	
	
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
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
        
        new GetHomeDataTask().execute(mUser.getToken());
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
        if(mUser != null){
            nameTx.setText(mUser.getName());
            jobTx.setText(mUser.getDuty());
        }
        noticeTx = (TextView) findViewById(R.id.home_notice_unread);
        mailTx = (TextView) findViewById(R.id.home_mail_unread);
        meetingTx = (TextView) findViewById(R.id.home_meetting_unread);
        workFlowTx = (TextView) findViewById(R.id.home_schedule_unread);
        contactTx = (TextView) findViewById(R.id.home_contact_unread);
        contactLayout = (RelativeLayout) findViewById(R.id.home_contact);
        contactLayout.setOnClickListener(this);
        findViewById(R.id.home_notice).setOnClickListener(this);
        findViewById(R.id.home_setting).setOnClickListener(this);
    }
    
    private void updateHomeData(HomeData homeData){
        if(homeData == null){
            Toast.makeText(this, " ˝æ›º”‘ÿ ß∞‹£¨«Î÷ÿ ‘£°", Toast.LENGTH_SHORT).show();
            return;
        }
        String unRead = "Œ¥∂¡";
        noticeTx.setText(homeData.getNoticeCount()+unRead);
        mailTx.setText(homeData.getMessageCount()+unRead);
        meetingTx.setText(homeData.getMeetingCount()+unRead);
        workFlowTx.setText(homeData.getWorkflowCount()+unRead);
        contactTx.setText(homeData.getAddressCount()+unRead);
        
    }

    @Override
    public void onClick( View v ) {
        switch (v.getId()) {
        case R.id.home_notice:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    NetEngine.getInstance(mContext).getAddressListUser(mUser.getToken(), "", "");
                }
            }).start();
            break;
        case R.id.home_contact:
            Intent intent = new Intent(this,ContactActivity.class);
            intent.putExtra("token", mUser.getToken());
            startActivity(intent);
            break;
        case R.id.home_setting:
            startActivity(new Intent(this, SettingActivity.class));
            break;
        default:
            break;
        }
    }
}

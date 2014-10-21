package net.wicp.yunjigroup.oa.ui;

import java.util.List;

import net.wicp.yunjigroup.oa.R;
import net.wicp.yunjigroup.oa.models.AddressGroup;
import net.wicp.yunjigroup.oa.net.NetEngine;
import net.wicp.yunjigroup.oa.utils.ActivitesStack;
import net.wicp.yunjigroup.oa.view.AddressGroupFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class ContactActivity extends FragmentActivity{
    private String mToken = null;
    private AddressGroupFragment groupFragment = null;
    private FragmentManager manager;
    private FragmentTransaction transaction = null;
    private Context mContext = null;
    
    class GroupsTask extends AsyncTask<Void, Void, List<AddressGroup>>{

        @Override
        protected List<AddressGroup> doInBackground( Void... params ) {
            return NetEngine.getInstance(mContext).getAddressList(mToken);
        }
        
        @Override
        protected void onPostExecute( List<AddressGroup> result ) {
            if(result != null){
                groupFragment.update(result);
            }
            super.onPostExecute(result);
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        ActivitesStack.getInstance().push(this);
        setContentView(R.layout.address_group);
        initViews();
        Intent intent = getIntent();
        mToken = intent.getStringExtra("token");
        
        new GroupsTask().execute();
    }
    
    public void initViews() {
        mContext = this;
        groupFragment = new AddressGroupFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, groupFragment, "group");
        transaction.commit();
    }

}

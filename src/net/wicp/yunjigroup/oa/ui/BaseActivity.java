package net.wicp.yunjigroup.oa.ui;

import net.wicp.yunjigroup.oa.utils.ActivitesStack;
import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity{
    
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        ActivitesStack.getInstance().push(this);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitesStack.getInstance().pop(this);
    }
    
}

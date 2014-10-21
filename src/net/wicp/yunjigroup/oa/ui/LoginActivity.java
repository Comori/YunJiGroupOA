package net.wicp.yunjigroup.oa.ui;

import java.io.IOException;

import net.wicp.yunjigroup.oa.R;
import net.wicp.yunjigroup.oa.models.User;
import net.wicp.yunjigroup.oa.net.NetEngine;
import net.wicp.yunjigroup.oa.utils.Contants;
import net.wicp.yunjigroup.oa.utils.Utils;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements OnClickListener {

    private EditText       userNameEdt, passwdEdt;
    private Button          sureBtn;
    private String         userName, passwd;
    private ProgressDialog dialog = null;
    private CheckBox remeberCB = null;

    /**
     * 登陆的task
     * 
     * @author chenqiang5
     * 
     */
    class LoginTask extends AsyncTask<String, Void, User> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected User doInBackground( String... arg0 ) {
            User user = NetEngine.getInstance(mContext).login(arg0[0], arg0[1]);
            return user;
        }

        @Override
        protected void onPostExecute( User result ) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (result != null
                    && (TextUtils.isEmpty(result.getError()) || result.getError().equalsIgnoreCase(
                            "null"))) {
                Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra(Contants.SP_USER, result);
                startActivity(intent);
                finish();
                Utils.setLoginState(LoginActivity.this, remeberCB.isChecked());
                try {
                    Utils.saveObjToSP(LoginActivity.this, Contants.SP_USER, result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (result != null && !TextUtils.isEmpty(result.getError())
                        && !"null".equalsIgnoreCase(result.getError())) {
                    Toast.makeText(LoginActivity.this, result.getError(), Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initViews();

    }

    @Override
    public void initViews() {
        userNameEdt = (EditText) findViewById(R.id.login_name);
        passwdEdt = (EditText) findViewById(R.id.login_passwd);
        sureBtn = (Button) findViewById(R.id.login_sure);
        remeberCB = (CheckBox) findViewById(R.id.login_remeber);
        sureBtn.setOnClickListener(this);
        dialog = new ProgressDialog(this);
        dialog.setTitle("登陆");
        dialog.setCancelable(false);
        dialog.setMessage("正在登陆...");
        dialog.setIndeterminate(false);
    }

    @Override
    public void onClick( View v ) {
        switch (v.getId()) {
        case R.id.login_sure:
            userName = userNameEdt.getText().toString();
            passwd = passwdEdt.getText().toString();
            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passwd)) {
                if (TextUtils.isEmpty(userName)) {
                    userNameEdt.setError("用户名不能为空!");
                }
                if (TextUtils.isEmpty(passwd)) {
                    passwdEdt.setError("密码不能为空!");
                }
            } else {
                new LoginTask().execute(userName, passwd);
            }
            break;
        default:
            break;
        }
    }

}

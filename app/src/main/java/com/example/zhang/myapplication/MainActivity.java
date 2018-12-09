package com.example.zhang.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG ="MainActivity" ;
    //用户名
    private EditText mEtUsername;
    //密码
    private EditText mEtPwd;
    //登录按键
    private Button mBtnLogin;
    private TextView mTvResult;

    private String url ="http://192.168.1.101:8080/member/regist2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    /**
     * 初始化组件
     */
    private void initView() {

        mEtUsername = (EditText) findViewById(R.id.login_et_name);
        mEtPwd = (EditText) findViewById(R.id.login_et_pwd);

        mBtnLogin = (Button) findViewById(R.id.login_btn_login);


        mTvResult = (TextView) findViewById(R.id.login_tv_result);

    }

    /**
     * 设置监听器
     */
    private void initListener() {

        mBtnLogin.setOnClickListener(this);


    }

    /*
    单击事件监听
     */
    @Override
    public void onClick(View v) {
        if(v==mBtnLogin){
            login();
        }
    }

    /*
    登录
     */
    private void login() {

        final String username = mEtUsername.getText().toString().trim();
        final String password = mEtPwd.getText().toString().trim();


        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(){
            @Override
            public void run() {

                HttpUtils httpUtils = new HttpUtils();
                //转换为JSON
                String user = httpUtils.bolwingJson(username, password);


                try {
                    jsonToList(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //String user ="{'username':" + username + ","+"'password':"+password+"}";

                Log.d(TAG, "user:" + user);

                try {
                    final String result = httpUtils.login(url, username,password);
                    Log.d(TAG, "结果:" + result);
                    //更新UI,在UI线程中
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if("success".equals(result)){

                                mTvResult.setText("登录成功");

                            }else{
                                mTvResult.setText("甘兆冬大傻逼");
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        }.start();


    }


    private void jsonToList(String arrayStr) throws JSONException {
        JSONArray jArray = new JSONArray(arrayStr);//假设str是你在服务端接收到的json字符串.
        JSONObject json_data = jArray.getJSONObject(0);
        String account1=json_data.getString("username");//userName对应你当时put时的名字
        String password1=json_data.getString("password");//passWord对应你当时put时的名字
    }
}
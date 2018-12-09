package com.example.zhang.myapplication;

import com.example.zhang.myapplication.model.LoginCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhang测试 on 2018/12/2.
 */

/**
 * Created by zhang测试 on 2018/12/2.
 */

public class HttpUtils { OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String login(String url, String username,String password) throws IOException {
        //把请求的内容字符串转换为json
        //  RequestBody body = RequestBody.create(JSON, json);
        //RequestBody formBody = new FormEncodingBuilder()
        String regFlag;

        String regMsg = null;
        RequestBody requestBody=new FormBody.Builder()
                .add("member_password",password)
                .add("member_name", username)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
     //  System.out.println(response.body().string());
        String result = response.body().string();


        Gson gson = new Gson();

        LoginCheck jsonBean = gson.fromJson(result, LoginCheck.class);
/*        try{
     *//*       JSONArray jsonArray=new JSONArray(result);
          *//**//*  for(int i=0;i<jsonArray.length();i++)
            {*//**//*
                JSONObject jsonObject=jsonArray.getJSONObject(0);
                regFlag=jsonObject.getString("regFlag");
                 regMsg=jsonObject.getString("regMsg");*//*
              *//*  idList.add(id);
                nameList.add(name);*//*
          *//*  }*//*
        } catch (JSONException e) {
            e.printStackTrace();
        }*/



        return jsonBean.regFlag;


    }


    public String bolwingJson(String username, String password) {

        String arrayStr="[{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}]";
        //转化为list

        return /* {"name":"jifeng","company":"taobao"}; */
                "[{\"username\":\"" + username + "\"," + "\"password\":\"" + password+ "\"}]";
        //     "{'username':" + username + ","+"'password':"+password+"}";



    }



}
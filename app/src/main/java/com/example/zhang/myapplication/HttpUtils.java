package com.example.zhang.myapplication;

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

public class HttpUtils { OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String login(String url, String json) throws IOException {
        //把请求的内容字符串转换为json
        RequestBody body = RequestBody.create(JSON, json);
        //RequestBody formBody = new FormEncodingBuilder()

        RequestBody requestBody=new FormBody.Builder()
                .add("password","123456")
                .add("username","45454")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        String result = response.body().string();


        return result;


    }


    public String bolwingJson(String username, String password) {

        String arrayStr="[{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}]";
        //转化为list

        return /* {"name":"jifeng","company":"taobao"}; */
        "[{\"username\":\"" + username + "\"," + "\"password\":\"" + password+ "\"}]";
        //     "{'username':" + username + ","+"'password':"+password+"}";



    }



}
package com.example.profitplus.presenter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.profitplus.constant.NetworkTask;
import com.example.profitplus.constant.Url;

import org.json.JSONObject;
import java.util.HashMap;

public class SigninPresenter {

    private Context context;
    private Signin signin;

    public SigninPresenter(Context context, Signin signin) {
        this.context = context;
        this.signin = signin;
    }

    public interface Signin {
        void success(String response);

        void error(String response);

        void fail(String response);
    }

    public void VerifyMobile(){
        HashMap<String,String> postData = new HashMap<>();
       // postData.put("mobile",PhoneHolder);
        Log.e("postData",postData.toString());
        new NetworkTask(context, Url.VERIFYLOGIN, new NetworkTask.MultiPartRequest() {
            @Override
            public void myResponseMethod(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");
                    if (status.equals("200")) {
                        String result = object.getString("result");
                        JSONObject object1 = new JSONObject(result);
                        String otp = object1.getString("otp");
                        String userid = object1.getString("user_id");
                    } else if (status.equals("404")) {
                        String message = object.getString("message");
                        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("exception", e.toString());
                }

            }
        },postData,true);
    }
}

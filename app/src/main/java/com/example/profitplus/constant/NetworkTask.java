package com.example.profitplus.constant;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.profitplus.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.body.FilePart;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.async.http.body.StringPart;
import com.koushikdutta.ion.Ion;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import androidx.fragment.app.FragmentActivity;
import dmax.dialog.SpotsDialog;


public class NetworkTask {
    private Context context;
    //private AlertDialog loadingDialog;
    private AlertDialog progressDialog;
    private String url,type;
    private HashMap<String, File> postFiles;
    private HashMap<String, String> postData;
    private String loginOtp;
    private MultiPartRequest request;
    private Boolean isShow;


    public NetworkTask(Context context, String url,
                       MultiPartRequest request,
                       HashMap<String, File> imageOne ,
                       HashMap<String, String> postData, boolean isShow) {
        this.context=context;
        this.url=url;
        this.postFiles=imageOne;
        this.postData=postData;
        this.request=request;
        this.isShow=isShow;
        this.type="POST";
        if (isConnectingToInternet()) {
            showPopup();
            sendData();
        }
    }


    public NetworkTask(Context context, String url,
                       MultiPartRequest request,
                       HashMap<String, String> postData, boolean isShow) {
        this.context=context;
        this.url=url;
        this.postFiles=new HashMap<>();
        this.postData=postData;
        this.request=request;
        this.isShow=isShow;
        this.type="POST";
        if (isConnectingToInternet()) {
            showPopup();
            sendData();
        }
    }

    public NetworkTask(FragmentActivity activity, String venderList, MultiPartRequest multiPartRequest) {
        this.context=context;
        this.url=url;
        this.postFiles=new HashMap<>();
        this.postData=new HashMap<>();
        this.request=request;
        this.isShow=isShow;
        this.type="GET";
        if (isConnectingToInternet()) {
            showPopup();
            sendData();
        }
    }

    // Ankit ///

    public NetworkTask(Context context,String url,MultiPartRequest request,String otpData,boolean isShow){
        this.context = context;
        this.url=url;
        this.request =request;
        this.isShow = isShow;
        this.loginOtp = otpData;
        this.type="POST";
        if (isConnectingToInternet()) {
            showPopup();
            sendData();
        }
    }

    private void sendOtpData() {

    }

    ////

    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            @SuppressLint("MissingPermission") NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (NetworkInfo anInfo : info)
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED)
                        return true;
        }
        return false;
    }

    private void sendData() {

        List<Part> data = new ArrayList<>();
        if (postData.size()!=0) {
            for (String key : postData.keySet()) {
                String val = postData.get(key);
                if (val==null)
                    val="";
                data.add(new StringPart(key, val));
            }
        }

        List<Part> files = new ArrayList<>();
        if (postFiles.size()!=0) {
            for (String key : postFiles.keySet()) {
                File val = postFiles.get(key);
                if (val!=null)
                    files.add(new FilePart(key, val));
            }
        }

        if (files.size()!=0 && data.size()!=0) {
            Ion.with(context)
                    .load(type, url)
                    .addMultipartParts(files)
                    .addMultipartParts(data)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {
                                //Log.e("res_file+data",result+"_");
                                request.myResponseMethod(result);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                request.myResponseMethod("");
                            } finally {
                                progressDialog.dismiss();
//                                progressDialog.cancel();
                            }
                        }
                    });
        } else if (data.size()!=0) {
            Ion.with(context)
                    .load(type, url)
                    .addMultipartParts(data)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {
                                //Log.e("res_data",result+"_");
                                request.myResponseMethod(result);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                request.myResponseMethod("");
                            } finally {
                                progressDialog.dismiss();
//                                progressDialog.cancel();
                            }
                        }
                    });
        } else {
            Ion.with(context)
                    .load(type, url)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {
                                //Log.e("res",result+"_");
                                request.myResponseMethod(result);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                request.myResponseMethod("");
                            } finally {
                                progressDialog.dismiss();
//                                progressDialog.cancel();
                            }
                        }
                    });
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void showPopup() {
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
        if (isShow)
            progressDialog.show();
    }
    public interface MultiPartRequest {

        void myResponseMethod(String response);
    }


}

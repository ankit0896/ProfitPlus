package com.example.profitplus.presenter;

import android.app.AlertDialog;
import android.content.Context;

import com.example.profitplus.R;
import com.example.profitplus.constant.RetrofitClient;
import com.example.profitplus.model.termConditionsResponse.TermConditionResponse;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermConditionPresenter {
    private Context context;
    private TNC tnc;
    private AlertDialog progressDialog;

    public TermConditionPresenter(Context context, TNC tnc) {
        this.context = context;
        this.tnc = tnc;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
    }

    public interface TNC{
        void success(String response);
        void error(String response);
        void fail(String response);
    }

    public void getTNCData(String token){
        progressDialog.show();
        Call<TermConditionResponse> responseCall = RetrofitClient.getInstance().getMyApi().getTncData(token);
        responseCall.enqueue(new Callback<TermConditionResponse>() {
            @Override
            public void onResponse(Call<TermConditionResponse> call, Response<TermConditionResponse> response) {
                if(response.isSuccessful()){
                    TermConditionResponse responses = response.body();
                    try {
                        progressDialog.dismiss();
                        tnc.success(responses.getData());
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        tnc.error("Server Error Please try after Some time.");
                    }
                }else{
                    progressDialog.dismiss();
                    tnc.error("Server Error Please try after Some time.");
                }
            }

            @Override
            public void onFailure(Call<TermConditionResponse> call, Throwable t) {
                progressDialog.dismiss();
                tnc.fail("Server Error Please try after Some time.");
            }
        });
    }
}

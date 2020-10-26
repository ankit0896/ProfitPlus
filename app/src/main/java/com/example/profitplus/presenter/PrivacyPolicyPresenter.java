package com.example.profitplus.presenter;

import android.app.AlertDialog;
import android.content.Context;

import com.example.profitplus.R;
import com.example.profitplus.constant.RetrofitClient;
import com.example.profitplus.model.privacyPolicyResponse.PrivacyPolicyResponse;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyPolicyPresenter {
    private Context context;
    private PrivacyPolicyData policyData;
    private AlertDialog progressDialog;

    public PrivacyPolicyPresenter(Context context, PrivacyPolicyData policyData) {
        this.context = context;
        this.policyData = policyData;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
    }

    public interface PrivacyPolicyData {
        void success(String data,String payment);

        void error(String response);

        void fail(String response);
    }


    public void getPrivacyPolicy(String token){
        progressDialog.show();
        Call<PrivacyPolicyResponse> responseCall = RetrofitClient.getInstance().getMyApi().getPrivacyPolicyData(token);
        responseCall.enqueue(new Callback<PrivacyPolicyResponse>() {
            @Override
            public void onResponse(Call<PrivacyPolicyResponse> call, Response<PrivacyPolicyResponse> response) {
                if(response.isSuccessful()){
                    PrivacyPolicyResponse privacyPolicyResponse = response.body();
                    try {
                        progressDialog.dismiss();
                        policyData.success(privacyPolicyResponse.getData(),privacyPolicyResponse.getPaymentCondition());
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        policyData.error("Server error. Please try after Sometimes.");
                    }
                }
            }

            @Override
            public void onFailure(Call<PrivacyPolicyResponse> call, Throwable t) {
                progressDialog.dismiss();
                policyData.fail("Server error. Please try after Sometimes.");
            }
        });
    }
}

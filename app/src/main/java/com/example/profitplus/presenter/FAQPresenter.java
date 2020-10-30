package com.example.profitplus.presenter;

import android.app.AlertDialog;
import android.content.Context;

import com.example.profitplus.R;
import com.example.profitplus.constant.RetrofitClient;
import com.example.profitplus.model.faqstockmodel.FaqStockModel;
import com.example.profitplus.model.faqstockmodel.StockFaq;
import com.example.profitplus.model.privacyPolicyResponse.PrivacyPolicyResponse;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQPresenter {
    private Context context;
    private FAQPre faqPre;
    private AlertDialog progressDialog;

    public FAQPresenter(Context context, FAQPre faqPre) {
        this.context = context;
        this.faqPre = faqPre;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
    }

    public interface FAQPre {
        void success(List<StockFaq> faqArrayList);

        void error(String response);

        void fail(String response);
    }
    public void GetFaq(String token){

        Call<FaqStockModel> responseCall = RetrofitClient.getInstance().getMyApi().getFaqStockData(token);
        responseCall.enqueue(new Callback<FaqStockModel>() {
            @Override
            public void onResponse(Call<FaqStockModel> call, Response<FaqStockModel> response) {
                progressDialog.show();
                if(response.isSuccessful()){
                    FaqStockModel faqStockModel = response.body();
                    try {
                        progressDialog.dismiss();
                        faqPre.success(faqStockModel.getStockFaqs());
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        faqPre.error("Server error. Please try after Sometimes.");
                    }
                }
            }

            @Override
            public void onFailure(Call<FaqStockModel> call, Throwable t) {
                progressDialog.dismiss();
                faqPre.fail("Server error. Please try after Sometimes.");
            }
        });
    }
}

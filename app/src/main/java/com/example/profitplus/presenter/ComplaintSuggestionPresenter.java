package com.example.profitplus.presenter;

import android.app.AlertDialog;
import android.content.Context;

import com.example.profitplus.R;
import com.example.profitplus.model.faqstockmodel.StockFaq;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class ComplaintSuggestionPresenter {

    private Context context;
    private AlertDialog progressDialog;
    private ComplaintSuggestionPre suggestionPre;

    public ComplaintSuggestionPresenter(Context context, ComplaintSuggestionPre suggestionPre) {
        this.context = context;
        this.suggestionPre = suggestionPre;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
    }

    public interface ComplaintSuggestionPre {
        void success(List<String> faqArrayList);

        void error(String response);

        void fail(String response);
    }



    public void sendSuggestions(String token,String firstname,String lastName,String mobile,String email,String comment){

    }
}

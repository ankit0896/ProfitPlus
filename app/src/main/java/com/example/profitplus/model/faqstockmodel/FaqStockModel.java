package com.example.profitplus.model.faqstockmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaqStockModel {
    @SerializedName("stock_faqs")
    @Expose
    private List<StockFaq> stockFaqs = null;
    @SerializedName("access_token")
    @Expose
    private boolean accessToken;

    public List<StockFaq> getStockFaqs() {
        return stockFaqs;
    }

    public void setStockFaqs(List<StockFaq> stockFaqs) {
        this.stockFaqs = stockFaqs;
    }

    public boolean isAccessToken() {
        return accessToken;
    }

    public void setAccessToken(boolean accessToken) {
        this.accessToken = accessToken;
    }
}

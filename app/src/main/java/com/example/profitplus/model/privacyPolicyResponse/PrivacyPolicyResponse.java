package com.example.profitplus.model.privacyPolicyResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrivacyPolicyResponse {
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("payment_condition")
    @Expose
    private String paymentCondition;
    @SerializedName("message")
    @Expose
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.example.profitplus.model.otpResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPResponse {

    @SerializedName("user")
    @Expose
    private OTPUserResponse user;
    @SerializedName("message")
    @Expose
    private String message;

    public OTPUserResponse getUser() {
        return user;
    }

    public void setUser(OTPUserResponse user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

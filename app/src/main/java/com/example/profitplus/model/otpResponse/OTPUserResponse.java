package com.example.profitplus.model.otpResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPUserResponse {
    @SerializedName("otp")
    @Expose
    private Integer otp;

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
}

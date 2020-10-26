package com.example.profitplus.model.loginOTPResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginSuccessResponse {
    @SerializedName("user")
    @Expose
    private LoginOTPUserResponse user;
    @SerializedName("access_token")
    @Expose
    private LoginOTPAccessToken accessToken;

    public LoginOTPUserResponse getUser() {
        return user;
    }

    public void setUser(LoginOTPUserResponse user) {
        this.user = user;
    }

    public LoginOTPAccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(LoginOTPAccessToken accessToken) {
        this.accessToken = accessToken;
    }

}

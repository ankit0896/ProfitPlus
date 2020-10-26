package com.example.profitplus.model.registerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterSuccessResponse {
    @SerializedName("user")
    @Expose
    private RegisterUserResponse user;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("message")
    @Expose
    private String message;

    public RegisterUserResponse getUser() {
        return user;
    }

    public void setUser(RegisterUserResponse user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

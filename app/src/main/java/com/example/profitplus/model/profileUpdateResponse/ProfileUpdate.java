package com.example.profitplus.model.profileUpdateResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileUpdate {
    @SerializedName("user")
    @Expose
    private ProfileUModel user;
    @SerializedName("message")
    @Expose
    private String message;

    public ProfileUModel getUser() {
        return user;
    }

    public void setUser(ProfileUModel user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

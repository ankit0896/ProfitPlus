package com.example.profitplus.constant;

import com.example.profitplus.model.privacyPolicyResponse.PrivacyPolicyResponse;
import com.example.profitplus.model.registerResponse.RegisterSuccessResponse;
import com.example.profitplus.model.loginOTPResponse.LoginSuccessResponse;
import com.example.profitplus.model.otpResponse.OTPResponse;
import com.example.profitplus.model.termConditionsResponse.TermConditionResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    // User Registration
    @POST("register")
    @FormUrlEncoded
    Call<RegisterSuccessResponse> verifyaAndRegister(@Field("otp") String otp, @Field("first_name") String firstName, @Field("last_name") String lastName, @Field("email") String email, @Field("country") String country, @Field("gender") String gender, @Field("mobile") String mobileNumber, @Field("password") String password, @Field("password_confirm") String confirmPassword);

    @POST("register")
    @FormUrlEncoded
    Call<OTPResponse> sendOTP(@Field("mobile") String mobileNumber,@Field("email") String email);

    @POST("otp")
    @FormUrlEncoded
    Call<OTPResponse> sendOTPLogin(@Field("mobile") String mobile);

    @POST("login")
    @FormUrlEncoded
    Call<LoginSuccessResponse> verifyMobileWithOTP(@Field("mobile") String mobile, @Field("otp") String otp);

    @POST("password_login")
    @FormUrlEncoded
    Call<LoginSuccessResponse> verifyMobileWithPassword(@Field("mobile") String mobile, @Field("password") String password);

    @POST("terms")
    @FormUrlEncoded
    Call<TermConditionResponse> getTncData(@Field("token") String token);

    @POST("policy")
    @FormUrlEncoded
    Call<PrivacyPolicyResponse> getPrivacyPolicyData(@Field("token") String token);
}

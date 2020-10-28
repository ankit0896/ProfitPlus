package com.example.profitplus.presenter;

import android.app.AlertDialog;
import android.content.Context;

import com.example.profitplus.R;
import com.example.profitplus.constant.RetrofitClient;
import com.example.profitplus.model.registerResponse.RegisterSuccessResponse;
import com.example.profitplus.model.otpResponse.OTPResponse;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter {
    private Context context;
    private SignUpOTP signUpOTP;
    private SignUpSaveData signUpSaveData;
    private AlertDialog progressDialog;


    public SignUpPresenter(Context context, SignUpOTP signUpOTP) {
        this.context = context;
        this.signUpOTP = signUpOTP;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
    }

    public SignUpPresenter(Context context, SignUpSaveData signUpSaveData) {
        this.context = context;
        this.signUpSaveData = signUpSaveData;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
    }

    public interface SignUpOTP {
        void success(OTPResponse otpResponse);

        void fail(String response);

        void error(String response);
    }

    public interface SignUpSaveData {
        void success(RegisterSuccessResponse registerSuccessResponse);

        void fail(String response);

        void error(String response);
    }

    /*
    Send otp to mobile number
    This methods gives you otp for Registration Part
     */
    public void sendOtpregister(String mobile) {
        progressDialog.show();
        if (mobile.isEmpty()) {
            progressDialog.dismiss();
            signUpOTP.error("Please Enter valid mobile Numeber.");
        } else {
            Call<OTPResponse> responseCall = RetrofitClient.getInstance().getMyApi().sendOTP(mobile,"");
            responseCall.enqueue(new Callback<OTPResponse>() {
                @Override
                public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                    if (response.isSuccessful()) {
                        try {
                            OTPResponse otpResponse = response.body();
                            progressDialog.dismiss();
                            signUpOTP.success(otpResponse);
                        } catch (Exception e) {
                            progressDialog.dismiss();
                            signUpOTP.error("Server error Please Try after some times.");
                        }
                    } else if (response.code() == 400) {
                        progressDialog.dismiss();
                        signUpOTP.error("Server error Please Try after some times.");
                    }
                }

                @Override
                public void onFailure(Call<OTPResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    signUpOTP.fail("Server error Please Try after some times.");
                }
            });
        }
    }

    /*
        Send otp to mobile number
        This methods saves your data for Registration Part
   */
    public void verifyAndSaveData(String otp, String fName, String lName, String email, String gender, String country, String mNumber, String pass) {
        progressDialog.show();
        Call<RegisterSuccessResponse> responseCall = RetrofitClient.getInstance().getMyApi().verifyaAndRegister(otp, fName, lName, email, country, gender, mNumber, pass);
        responseCall.enqueue(new Callback<RegisterSuccessResponse>() {
            @Override
            public void onResponse(Call<RegisterSuccessResponse> call, Response<RegisterSuccessResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        RegisterSuccessResponse registerSuccessResponse = response.body();
                        progressDialog.dismiss();
                        signUpSaveData.success(registerSuccessResponse);
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        signUpSaveData.error("Server error. Please try after some times");
                    }
                } else {
                    progressDialog.dismiss();
                    signUpSaveData.error("Server error. Please try after some times");
                }
            }

            @Override
            public void onFailure(Call<RegisterSuccessResponse> call, Throwable t) {
                progressDialog.dismiss();
                signUpSaveData.fail("Server error. Please try after some times");
            }
        });
    }
}

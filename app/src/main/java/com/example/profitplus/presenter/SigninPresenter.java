package com.example.profitplus.presenter;

import android.app.AlertDialog;
import android.content.Context;

import com.example.profitplus.R;
import com.example.profitplus.constant.RetrofitClient;
import com.example.profitplus.model.loginOTPResponse.LoginSuccessResponse;
import com.example.profitplus.model.otpResponse.OTPResponse;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninPresenter {

    private Context context;
    private LoginMobile signin;
    private LoginOTPMobile otpMobile;
    private AlertDialog progressDialog;

    public SigninPresenter(Context context, LoginMobile signin) {
        this.context = context;
        this.signin = signin;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
    }

    public SigninPresenter(Context context, LoginOTPMobile mobile) {
        this.context = context;
        this.otpMobile = mobile;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();

    }

    public interface LoginMobile {
        void success(String response);

        void error(String response);

        void fail(String response);
    }

    public interface LoginOTPMobile {
        void success(String call, LoginSuccessResponse response);

        void error(String response);

        void fail(String response);
    }

//    public void VerifyMobile(String PhoneHolder) {
//        HashMap<String, String> postData = new HashMap<>();
//        postData.put("mobile", PhoneHolder);
//        Log.e("postData", postData.toString());
//        new NetworkTask(context, Url.VERIFYLOGIN, new NetworkTask.MultiPartRequest() {
//            @Override
//            public void myResponseMethod(String response) {
//
//                try {
//                    JSONObject object = new JSONObject(response);
//                    String status = object.getString("status");
//                    if (status.equals("200")) {
//                        String result = object.getString("result");
//                        JSONObject object1 = new JSONObject(result);
//                        String otp = object1.getString("otp");
//                        String userid = object1.getString("user_id");
//                        signin.success(otp);
//                    } else if (status.equals("404")) {
//                        String message = object.getString("message");
//                        signin.fail(message);
//                        //Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//                    Log.e("exception", e.toString());
//                    signin.error("server error try after sometime!");
//                }
//
//            }
//        }, postData, true);
//    }

    /*
    Login With OTP
    This method return otp using mobile number
     */
    public void SignInMobile(String mobile) {
        progressDialog.show();
        if (mobile.length() < 10) {
            progressDialog.dismiss();
            signin.error("Please Enter Valid Mobile Number.");
        } else {
            Call<OTPResponse> responseCall = RetrofitClient.getInstance().getMyApi().sendOTPLogin(mobile);
            responseCall.enqueue(new Callback<OTPResponse>() {
                @Override
                public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                    if (response.isSuccessful()) {
                        OTPResponse otpResponse = null;
                        try {
                            otpResponse = response.body();
                            progressDialog.dismiss();
                            signin.success((String.valueOf(otpResponse.getUser().getOtp())));
                        } catch (Exception e) {
                            progressDialog.dismiss();
                            signin.error(otpResponse.getMessage());
                        }
                    } else {
                        progressDialog.dismiss();
                        signin.error(response.message());
                    }
                }

                @Override
                public void onFailure(Call<OTPResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    signin.fail(t.getMessage());
                }
            });
        }
    }

    /*
   Login With OTP
   This method return User Details validating mobile number and OTP
    */
    public void singinWithOtp(String mobile, String otp) {
        progressDialog.show();
        if (otp.isEmpty()) {
            progressDialog.dismiss();
            otpMobile.error("Enter OTP or Signin With Password");
        } else {

            Call<LoginSuccessResponse> responseUserCall = RetrofitClient.getInstance().getMyApi().verifyMobileWithOTP(mobile, otp);
            responseUserCall.enqueue(new Callback<LoginSuccessResponse>() {
                @Override
                public void onResponse(Call<LoginSuccessResponse> call, Response<LoginSuccessResponse> response) {
                    if (response.isSuccessful()) {
                        LoginSuccessResponse otpResponseUser = response.body();
                        progressDialog.dismiss();
                        otpMobile.success("otp", otpResponseUser);
                    } else {
                        progressDialog.dismiss();
                        otpMobile.error("Incorrect OTP");
                    }
                }

                @Override
                public void onFailure(Call<LoginSuccessResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    otpMobile.fail("Server Error. Please try after sometimes.");
                }
            });
        }
    }

    /*
Login With OTP
This method return User Details validating mobile number and Password
 */
    public void singInWithPassword(String mobile, String password) {
        progressDialog.show();
        if (password.isEmpty()) {
            progressDialog.dismiss();
            otpMobile.error("Enter Password");
        } else {
            Call<LoginSuccessResponse> responseUserCall = RetrofitClient.getInstance().getMyApi().verifyMobileWithPassword(mobile, password);
            responseUserCall.enqueue(new Callback<LoginSuccessResponse>() {
                @Override
                public void onResponse(Call<LoginSuccessResponse> call, Response<LoginSuccessResponse> response) {
                    if (response.isSuccessful()) {
                        try {
                            LoginSuccessResponse otpResponseUser = response.body();
                            progressDialog.dismiss();
                            otpMobile.success("otp", otpResponseUser);
                        } catch (Exception e) {
                            progressDialog.dismiss();
                            otpMobile.error("Server error. Please try after some times.");
                        }
                    } else {
                        progressDialog.dismiss();
                        otpMobile.error("Incorrect OTP");
                    }
                }

                @Override
                public void onFailure(Call<LoginSuccessResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    otpMobile.fail("Server Error. Please try after sometimes.");
                }
            });
        }
    }

}

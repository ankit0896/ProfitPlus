package com.example.profitplus.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.chaos.view.PinView;
import com.example.profitplus.R;
import com.example.profitplus.constant.PreferenceManager;
import com.example.profitplus.model.UserModel;
import com.example.profitplus.model.loginOTPResponse.LoginOTPUserResponse;
import com.example.profitplus.model.loginOTPResponse.LoginSuccessResponse;
import com.example.profitplus.presenter.SigninPresenter;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginOtpActivity extends AppCompatActivity implements SigninPresenter.LoginOTPMobile {


    @BindView(R.id.tv_login_with_password_text)
    TextView continueWithPass;
    @BindView(R.id.tv_otp_login_back_to_otp)
    TextView backtootp;
    @BindView(R.id.tv_login_otp_sms_sent_to)
    TextView smssentto;
    @BindView(R.id.ll_login_otp_layout)
    LinearLayout otplayout;
    @BindView(R.id.ll_login_password_layout)
    LinearLayout passwordlayout;
    @BindView(R.id.otp_login_pin_view)
    PinView otploginPinView;
    @BindView(R.id.et_login_password_otp)
    EditText password;
    @BindView(R.id.card_login_password_otp)
    CardView loginPassword;
    @BindView(R.id.card_login_send_otp)
    CardView loginOTP;
    @BindView(R.id.tv_login_otp_didotget_sms)
    TextView didnotgetotp;
    @BindView(R.id.iv_login_otp_backbtn)
    ImageView back;
    @BindView(R.id.tv_title_login_otp)
    TextView title;
    @BindView(R.id.alert)
    RelativeLayout relativeLayout;
    String mobileNumber, otp;
    Intent intent;
    SigninPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }
        presenter = new SigninPresenter(LoginOtpActivity.this, LoginOtpActivity.this);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        intent = getIntent();
        otp = intent.getStringExtra("OTP");
        mobileNumber = intent.getStringExtra("mobileNumber");
        smssentto.setText("Sent view sms to +91 " + mobileNumber);
        otploginPinView.setText(otp);
        clickEvents();
    }

    private void clickEvents() {
        continueWithPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otplayout.setVisibility(View.GONE);
                passwordlayout.setVisibility(View.VISIBLE);
                title.setText("Password");
            }
        });
        backtootp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otplayout.setVisibility(View.VISIBLE);
                passwordlayout.setVisibility(View.GONE);
                title.setText("OTP");
            }
        });

        // Login with OTP -> OTP Button for Verification
        loginOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otploginPinView.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Please Enter OTP", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    vertifyOTPWithMobile(otploginPinView.getText().toString(), mobileNumber);
                }
            }
        });

        loginPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Please Enter Password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    presenter.singInWithPassword(mobileNumber,password.getText().toString());
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void vertifyOTPWithMobile(String otp, String mobileNumber) {
        presenter.singinWithOtp(mobileNumber, otp);
    }

    @Override
    public void success(String call, LoginSuccessResponse response) {
        LoginOTPUserResponse mobileOTPUserModel = response.getUser();
        UserModel model = new UserModel();
        model.setId("" + mobileOTPUserModel.getId());
        model.setfName(mobileOTPUserModel.getFirstName());
        model.setlName(mobileOTPUserModel.getLastName());
        model.setGender(mobileOTPUserModel.getGender());
        model.setMobile(mobileOTPUserModel.getMobile());
        model.setEmail(mobileOTPUserModel.getEmail());
        model.setToken(response.getAccessToken().getToken());
        model.setLoginStatus(true);
        PreferenceManager.getInstance(LoginOtpActivity.this).userLogin(model);
        startActivity(new Intent(LoginOtpActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @Override
    public void error(String response) {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, response, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void fail(String response) {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, response, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
package com.example.profitplus.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.chaos.view.PinView;
import com.example.profitplus.R;
import com.example.profitplus.constant.PreferenceManager;
import com.example.profitplus.model.registerResponse.RegisterSuccessResponse;
import com.example.profitplus.model.registerResponse.RegisterUserResponse;
import com.example.profitplus.model.UserModel;
import com.example.profitplus.presenter.SignUpPresenter;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OtpActivity extends AppCompatActivity implements SignUpPresenter.SignUpSaveData {

    @BindView(R.id.ll_register_layout) LinearLayout otpRegisterLayout;
    @BindView(R.id.tv_sms_sent_to) TextView smsSentTo;
    @BindView(R.id.otp_pin_view) PinView pinView;
    @BindView(R.id.card_send_otp) CardView sendOtp;
    @BindView(R.id.iv_otp_backbtn) ImageView backBtn;

    String otp;
    Intent intent;
    String fName, lName, email, gender, country, mNumber, pass, repass;
    PreferenceManager manager;
    SignUpPresenter signUpPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }
        ButterKnife.bind(this);
        signUpPresenter = new SignUpPresenter(OtpActivity.this,OtpActivity.this);
        manager = new PreferenceManager(OtpActivity.this);
        initViews();
    }

    private void initViews() {
        intent = getIntent();
        otp = this.intent.getStringExtra("otp");
        Bundle bundle = this.intent.getExtras();
        fName = bundle.getString("firstName");
        lName = bundle.getString("lastName");
        email = bundle.getString("email");
        gender = bundle.getString("gender");
        country = bundle.getString("country");
        mNumber = bundle.getString("mobileNumber");
        pass = bundle.getString("password");
        repass = bundle.getString("repassword");
        pinView.setText(otp);
        smsSentTo.setText("Sent view sms to 91 " + mNumber);
        clickEvents();
    }

    private void clickEvents() {
        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinView.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Please Enter OTP", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    verifyOTP(pinView.getText().toString(), fName, lName, email, gender, country, mNumber, pass, repass);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void verifyOTP(String otp, String fName, String lName, String email, String gender, String country, String mNumber, String pass, String repass) {
       signUpPresenter.verifyAndSaveData(otp,fName,lName,email,gender,country,mNumber,pass,repass);
    }

    @Override
    public void success(RegisterSuccessResponse registerSuccessResponse) {
        RegisterUserResponse otpUserResponse = registerSuccessResponse.getUser();
        UserModel model = new UserModel();
        model.setId(""+otpUserResponse.getId());
        model.setfName(otpUserResponse.getFirstName());
        model.setlName(otpUserResponse.getLastName());
        model.setGender(otpUserResponse.getGender());
        model.setMobile(otpUserResponse.getMobile());
        model.setEmail(otpUserResponse.getEmail());
        model.setToken(registerSuccessResponse.getAccessToken());
        model.setLoginStatus(true);
        PreferenceManager.getInstance(OtpActivity.this).userLogin(model);
        startActivity(new Intent(OtpActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void fail(String response) {
        Snackbar.make(getCurrentFocus(), "" + response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void error(String response) {
        Snackbar.make(getCurrentFocus(), "" + response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
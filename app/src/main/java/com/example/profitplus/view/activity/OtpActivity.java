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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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


public class OtpActivity extends AppCompatActivity implements SignUpPresenter.SignUpSaveData, View.OnClickListener {

    @BindView(R.id.alert)
    RelativeLayout relativeLayout;
    @BindView(R.id.ll_register_layout)
    LinearLayout otpRegisterLayout;
    @BindView(R.id.tv_sms_sent_to)
    TextView smsSentTo;
    @BindView(R.id.otp_pin_view)
    PinView pinView;
    @BindView(R.id.card_send_otp)
    CardView sendOtp;
    @BindView(R.id.iv_otp_backbtn)
    ImageView backBtn;
    Intent intent;
    String fName, lName, email, gender, country, mNumber, pass,otp;
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
        Bundle bundle = this.intent.getExtras();
        fName = bundle.getString("firstName");
        lName = bundle.getString("lastName");
        email = bundle.getString("email");
        gender = bundle.getString("gender");
        country = bundle.getString("country");
        mNumber = bundle.getString("mobileNumber");
        pass = bundle.getString("password");
        otp=bundle.getString("otp");
        otp = bundle.getString("otp");
        pinView.setText(otp);
        smsSentTo.setText("Sent view sms to 91 " + mNumber);
        sendOtp.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == sendOtp) {
            String otpHolder = pinView.getText().toString();
            if (otpHolder.isEmpty()) {
                Snackbar.make(v, "Please Enter OTP", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else if (otpHolder.equals(otp)) {
                signUpPresenter.verifyAndSaveData(otpHolder, fName, lName, email, gender, country, mNumber, pass);
            } else {
                Snackbar.make(v, "Wrong otp please try again", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

        if (v == backBtn) {
            onBackPressed();
        }
    }

    @Override
    public void success(RegisterSuccessResponse registerSuccessResponse) {
      //  Toast.makeText(this, ""+registerSuccessResponse.getUser().getReferralCode(), Toast.LENGTH_SHORT).show();
        RegisterUserResponse otpUserResponse = registerSuccessResponse.getUser();
        UserModel model = new UserModel();
        model.setId(""+otpUserResponse.getId());
        model.setfName(otpUserResponse.getFirstName());
        model.setlName(otpUserResponse.getLastName());
        model.setGender(otpUserResponse.getGender());
        model.setMobile(otpUserResponse.getMobile());
        model.setEmail(otpUserResponse.getEmail());
        model.setReferalCode(String.valueOf(otpUserResponse.getReferralCode()));
        model.setToken(registerSuccessResponse.getAccessToken());
        model.setLoginStatus(true);
        PreferenceManager.getInstance(OtpActivity.this).userLogin(model);
        startActivity(new Intent(OtpActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void fail(String response) {
        Snackbar.make(relativeLayout, "" + response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void error(String response) {
        Snackbar.make(relativeLayout, "" + response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
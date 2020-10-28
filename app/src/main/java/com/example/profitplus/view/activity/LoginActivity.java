package com.example.profitplus.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.profitplus.R;
import com.example.profitplus.presenter.SigninPresenter;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements SigninPresenter.LoginMobile, View.OnClickListener {

    @BindView(R.id.tv_new_user)
    TextView newuser;
    @BindView(R.id.tv_btn_login)
    TextView login_bnt;
    @BindView(R.id.et_login_mobileNumber)
    EditText loginMobile;
    private SigninPresenter presenter;
    String userMobileNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorRed));
        }
        presenter = new SigninPresenter(LoginActivity.this, LoginActivity.this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        newuser.setOnClickListener(this);
        login_bnt.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view==newuser){
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        }
        if (view==login_bnt){
            if (validateFields(view)) {
                presenter.SignInMobile(userMobileNumber);
            }
        }

    }

    private boolean validateFields(View v) {
        userMobileNumber = loginMobile.getText().toString().trim();

        if (userMobileNumber.isEmpty()) {
            Snackbar.make(v, "Please Enter Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (userMobileNumber.length() < 10) {
            Snackbar.make(v, "Please Enter Valid Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }

        return true;
    }

    @Override
    public void success(String response) {
        String otp = response;
        startActivity(new Intent(LoginActivity.this, LoginOtpActivity.class).putExtra("OTP", otp).putExtra("mobileNumber", userMobileNumber));
    }

    @Override
    public void error(String response) {
        Snackbar.make(getCurrentFocus(), "" + response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void fail(String response) {
        Snackbar.make(getCurrentFocus(), "" + response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}
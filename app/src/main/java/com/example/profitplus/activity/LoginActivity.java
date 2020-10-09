package com.example.profitplus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.profitplus.R;
import com.example.profitplus.presenter.SigninPresenter;

public class LoginActivity extends AppCompatActivity implements SigninPresenter.Signin {

    TextView newuser,login_bnt;
    private SigninPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorRed));
        }
        presenter=new SigninPresenter(LoginActivity.this,LoginActivity.this);
        initView();
    }

    private void initView() {
        newuser = findViewById(R.id.tv_new_user);
        login_bnt = findViewById(R.id.tv_btn_login);
        clickEvents();
    }

    private void clickEvents() {
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });
        login_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public void success(String response) {

    }

    @Override
    public void error(String response) {

    }

    @Override
    public void fail(String response) {

    }
}
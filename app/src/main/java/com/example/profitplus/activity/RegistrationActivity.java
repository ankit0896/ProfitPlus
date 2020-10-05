package com.example.profitplus.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.profitplus.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class RegistrationActivity extends AppCompatActivity {

    ImageView backArrow;
    CardView btn_registor;
    EditText fName,lName,eMail,mNumber,pass,rePass;
    String firstName,lastName,email,mobileNumber,password,rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }
        initViews();
    }

    private void initViews() {
        backArrow = findViewById(R.id.back_arrow_register);
        fName = findViewById(R.id.et_rFirstName);
        lName = findViewById(R.id.et_rLastName);
        eMail = findViewById(R.id.et_rEmailId);
        mNumber = findViewById(R.id.et_rMobileNumber);
        pass = findViewById(R.id.et_rPassword);
        rePass = findViewById(R.id.et_rConfirmPassword);
        btn_registor = findViewById(R.id.btn_register_card);


        clickEvents();

    }

    private void clickEvents() {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_registor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmpty()){
                    // TODO
                    startActivity(new Intent(RegistrationActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }

            }
        });
    }

    private boolean validateEmpty() {

        return true;
    }
}
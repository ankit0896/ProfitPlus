package com.example.profitplus.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.profitplus.R;
import com.example.profitplus.model.otpResponse.OTPResponse;
import com.example.profitplus.presenter.SignUpPresenter;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SignUpPresenter.SignUpOTP, View.OnClickListener {

    @BindView(R.id.back_arrow_register)
    ImageView backArrow;
    @BindView(R.id.btn_register_card)
    CardView btn_registor;
    @BindView(R.id.et_rFirstName)
    EditText fName;
    @BindView(R.id.et_rLastName)
    EditText lName;
    @BindView(R.id.et_rEmailId)
    EditText eMail;
    @BindView(R.id.et_rMobileNumber)
    EditText mNumber;
    @BindView(R.id.et_rPassword)
    EditText pass;
    @BindView(R.id.et_rConfirmPassword)
    EditText rePass;
    @BindView(R.id.gender_spinner)
    Spinner genderSpinner;

    Bundle userBundle = new Bundle();
    SignUpPresenter signUpPresenter;
    String firstName, lastName, email, mobileNumber, password, rePassword, country, gender;
    String[] genders = {"Male", "Female"};

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
        ButterKnife.bind(this);
        signUpPresenter = new SignUpPresenter(RegistrationActivity.this, RegistrationActivity.this);
        initViews();
    }

    private void initViews() {
        genderSpinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        genderSpinner.setAdapter(aa);
        backArrow.setOnClickListener(this);
        btn_registor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==backArrow){
            onBackPressed();
        }
        if(v==btn_registor){
            if (validateEmpty(v)) {
                signUpPresenter.sendOtpregister(mobileNumber);
            }
        }
    }




    private boolean validateEmpty(View view) {
        firstName = fName.getText().toString().trim();
        lastName = lName.getText().toString().trim();
        email = eMail.getText().toString().trim();
        mobileNumber = mNumber.getText().toString().trim();
        password = pass.getText().toString().trim();
        rePassword = rePass.getText().toString().trim();
        country = "India";
        if (firstName.isEmpty()) {
            Snackbar.make(view, "Please Enter First Name", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (lastName.isEmpty()) {
            Snackbar.make(view, "Please Enter Last Name", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (email.isEmpty()) {
            Snackbar.make(view, "Please Enter Email Address", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()==false) {
            Snackbar.make(view, "Please Enter Valid Email Address", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (mobileNumber.isEmpty()) {
            Snackbar.make(view, "Please Enter Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (mobileNumber.length() < 10) {
            Snackbar.make(view, "Please Enter Valid Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (password.isEmpty()) {
            Snackbar.make(view, "Please Enter Password", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (password.length() < 6) {
            Snackbar.make(view, "Password Should be atleat 6 Character", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (rePassword.isEmpty()) {
            Snackbar.make(view, "Please Re-Enter Your Password", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (!password.equalsIgnoreCase(rePassword)) {
            Snackbar.make(view, "Password Should be same", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender = genders[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void success(OTPResponse otpResponse) {
        String otp = String.valueOf(otpResponse.getUser().getOtp());
        userBundle.putString("firstName", firstName);
        userBundle.putString("lastName", lastName);
        userBundle.putString("email", email);
        userBundle.putString("mobileNumber", mobileNumber);
        userBundle.putString("country", country);
        userBundle.putString("gender", gender);
        userBundle.putString("password", password);
        userBundle.putString("otp", otp);
        startActivity(new Intent(RegistrationActivity.this, OtpActivity.class).putExtras(userBundle));
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
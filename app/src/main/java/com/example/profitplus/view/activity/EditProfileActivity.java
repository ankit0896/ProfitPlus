package com.example.profitplus.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.profitplus.R;
import com.example.profitplus.presenter.SignUpPresenter;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
    @BindView(R.id.gender_spinner)
    Spinner genderSpinner;
    Bundle userBundle = new Bundle();
    //SignUpPresenter signUpPresenter;
    String firstName, lastName, email, mobileNumber, password, rePassword, country, gender;
    String[] genders = {"Male", "Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }
        ButterKnife.bind(this);
        //signUpPresenter = new SignUpPresenter(EditProfileActivity.this, EditProfileActivity.this);
        initViews();
    }
    private void initViews() {
        genderSpinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        genderSpinner.setAdapter(aa);
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
                if (validateEmpty(view)) {
                    userBundle.putString("firstName", firstName);
                    userBundle.putString("lastName", lastName);
                    userBundle.putString("email", email);
                    userBundle.putString("mobileNumber", mobileNumber);
                    userBundle.putString("country", country);
                    userBundle.putString("gender", gender);
                    userBundle.putString("password", password);
                    userBundle.putString("repassword", rePassword);
                    //sendOtp(mobileNumber);
                }

            }
        });

    }
    private boolean validateEmpty(View view) {
        firstName = fName.getText().toString().trim();
        lastName = lName.getText().toString().trim();
        email = eMail.getText().toString().trim();
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
        if (!email.contains("@")) {
            Snackbar.make(view, "Please Enter Valid Email Address", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
package com.example.profitplus.view.activity;

import android.content.DialogInterface;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.profitplus.R;
import com.example.profitplus.constant.PreferenceManager;
import com.example.profitplus.model.UserModel;
import com.example.profitplus.model.profileUpdateResponse.ProfileUModel;
import com.example.profitplus.presenter.UpdateProfilePresenter;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, UpdateProfilePresenter.UpdateProfilePre {


    @BindView(R.id.tv_epMobile)
    EditText mobileField;
    @BindView(R.id.relative_update_profile)
    RelativeLayout relativeLayout;
    @BindView(R.id.back_arrow_register)
    ImageView backArrow;
    @BindView(R.id.btn_update_details_card)
    CardView btn_update_details;
    @BindView(R.id.et_epFirstName)
    EditText fName;
    @BindView(R.id.et_epLastName)
    EditText lName;
    @BindView(R.id.et_epEmailId)
    EditText eMail;
    //    @BindView(R.id.et_rMobileNumber)
//    EditText mNumber;
    @BindView(R.id.ep_gender_spinner)
    Spinner genderSpinner;
    UpdateProfilePresenter presenter;
    String uid, firstName, lastName, email, country, gender, token, mobile;
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
        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);
        presenter = new UpdateProfilePresenter(EditProfileActivity.this, EditProfileActivity.this);
        genderSpinner.setOnItemSelectedListener(this);
        UserModel userModel = PreferenceManager.getInstance(EditProfileActivity.this).getCustomer();
        uid = userModel.getId();
        token = userModel.getToken();
        mobile = userModel.getMobile();
        fName.setText(userModel.getfName());
        lName.setText(userModel.getlName());
        eMail.setText(userModel.getEmail());
        mobileField.setText(userModel.getMobile());
        gender = userModel.getGender();
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        genderSpinner.setAdapter(aa);

        backArrow.setOnClickListener(this);
        btn_update_details.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == backArrow) {
            onBackPressed();
        }
        if (v == btn_update_details) {
            if (validateEmpty(v)) {
                presenter.updateProfile(uid, token, firstName, lastName, gender, "India", email);
            }
        }
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
        if (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() == false) {
            Snackbar.make(view, "Please Enter Valid Email Address", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        gender = genders[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        gender = "Male";
    }

    @Override
    public void success(ProfileUModel profileUModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Profile Updated Successfully.").setCancelable(false)
                .setIcon(R.drawable.home_logo)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserModel model = new UserModel();
                        model.setId(uid);
                        model.setfName(profileUModel.getFirstName());
                        model.setlName(profileUModel.getLastName());
                        model.setGender(profileUModel.getGender());
                        model.setMobile(mobile);
                        model.setEmail(profileUModel.getEmail());
                        model.setToken(token);
                        model.setLoginStatus(true);
                        PreferenceManager.getInstance(EditProfileActivity.this).userLogin(model);
                        startActivity(new Intent(EditProfileActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                }).show();
    }

    @Override
    public void fail(String response) {
        Snackbar.make(relativeLayout, response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void error(String response) {
        Snackbar.make(relativeLayout, response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
package com.example.profitplus.view.activity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.profitplus.R;
import com.example.profitplus.constant.PreferenceManager;
import com.example.profitplus.presenter.ComplaintSuggestionPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintSuggestionActivity extends AppCompatActivity implements View.OnClickListener, ComplaintSuggestionPresenter.ComplaintSuggestionPre {

    @BindView(R.id.back_arrow_cs)
    ImageView back;
    @BindView(R.id.relative_complant_profile)
    RelativeLayout relativeLayout;
    @BindView(R.id.et_cs_FirstName)
    EditText firstName;
    @BindView(R.id.et_cs_LastName)
    EditText lastName;
    @BindView(R.id.et_cs_mobile)
    EditText mobile;
    @BindView(R.id.et_cs_EmailId)
    EditText email;
    @BindView(R.id.et_cs_comment)
    EditText comment;
    @BindView(R.id.btn_complant_card)
    CardView send;
    ComplaintSuggestionPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_suggestion);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorRed));
        }
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        presenter = new ComplaintSuggestionPresenter(ComplaintSuggestionActivity.this,ComplaintSuggestionActivity.this);
        send.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == send) {
            if (validate()) {
                presenter.sendSuggestions(PreferenceManager.getInstance(this).getCustomer().getToken(),firstName.getText().toString(),lastName.getText().toString(),mobile.getText().toString(),email.getText().toString(),comment.getText().toString());
            }
        }
        if (v == back) {
            onBackPressed();
        }
    }

    private boolean validate() {
        if (firstName.getText().toString().trim().isEmpty()) {
            Snackbar.make(relativeLayout, "Please Enter First Name", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (lastName.getText().toString().trim().isEmpty()) {
            Snackbar.make(relativeLayout, "Please Enter Last Name", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (email.getText().toString().trim().isEmpty()) {
            Snackbar.make(relativeLayout, "Please Enter Email Address", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (!TextUtils.isEmpty(email.getText().toString().trim()) && android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches() == false) {
            Snackbar.make(relativeLayout, "Please Enter Valid Email Address", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        if (mobile.getText().toString().trim().isEmpty()) {
            Snackbar.make(relativeLayout, "Please Enter Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }if (mobile.getText().toString().length() < 10) {
            Snackbar.make(relativeLayout, "Please Enter Valid Mobile Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }


        if (comment.getText().toString().trim().isEmpty()) {
            Snackbar.make(relativeLayout, "Please Enter Complants/Suggestions", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        return true;

    }

    @Override
    public void success(List<String> faqArrayList) {

    }

    @Override
    public void error(String response) {

    }

    @Override
    public void fail(String response) {

    }
}
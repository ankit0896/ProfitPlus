package com.example.profitplus.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.profitplus.R;
import com.example.profitplus.constant.PreferenceManager;
import com.example.profitplus.presenter.TermConditionPresenter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermsAndConditionActivity extends AppCompatActivity implements TermConditionPresenter.TNC, View.OnClickListener {

    @BindView(R.id.lltnclayout)
    LinearLayout linearLayout;
    @BindView(R.id.drawerlayouttc)
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @BindView(R.id.tc_list_menu_items)
    ListView list_menu;
    @BindView(R.id.tc_toolBar)
    Toolbar toolbar;
    @BindView(R.id.iv_tc_toggle)
    ImageView toggle;
    @BindView(R.id.nav_view_tc)
    NavigationView navigationView;
    @BindView(R.id.tv_tncData)
    TextView tncdata;
    String[] listItem;
    TermConditionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorRed));
        }
        ButterKnife.bind(this);
        presenter = new TermConditionPresenter(TermsAndConditionActivity.this, TermsAndConditionActivity.this);
        initNavigation();
    }

    private void initNavigation() {
        TextView nav_view_name;
        View header = navigationView.findViewById(R.id.navheader);
        nav_view_name = (TextView) header.findViewById(R.id.nav_view_user_name);
        nav_view_name.setText(PreferenceManager.getInstance(TermsAndConditionActivity.this).getCustomer().getfName() + " " + PreferenceManager.getInstance(TermsAndConditionActivity.this).getCustomer().getlName());

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        loadMenuItemList();
        toggle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==toggle){
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void loadMenuItemList() {
        listItem = getResources().getStringArray(R.array.menu_option);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.menu_item_list, R.id.tv_menu_item_list, listItem);
        list_menu.setAdapter(adapter);

        list_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value = adapter.getItem(position);
                if (value.equals("Dashboard")) {
                    startActivity(new Intent(TermsAndConditionActivity.this, HomeActivity.class));
                    finish();
                }
                if (value.equals("Privacy Policy")) {
                    startActivity(new Intent(TermsAndConditionActivity.this, PrivacyPolicyActivity.class));
                    finish();
                }
                if (value.equals("Terms & Condition")) {
                    startActivity(new Intent(TermsAndConditionActivity.this, TermsAndConditionActivity.class));
                    finish();
                }
                if (value.equals("My Account")) {
                    startActivity(new Intent(TermsAndConditionActivity.this, EditProfileActivity.class));
                }
                if (value.equals("Refer & Earn")) {
                    startActivity(new Intent(TermsAndConditionActivity.this, ReferAndEarnActivity.class));
                    finish();
                }
                if (value.equals("Complants/Suggestions")) {
                    startActivity(new Intent(TermsAndConditionActivity.this, ComplaintSuggestionActivity.class));
                }
                if (value.equals("FAQs")) {
                    startActivity(new Intent(TermsAndConditionActivity.this, FaqsActivity.class));
                    finish();
                }
                if (value.equals("Logout")) {
                    startActivity(new Intent(TermsAndConditionActivity.this, OnBoardActivity.class));
                    PreferenceManager.clear();
                    finish();
                }

            }
        });


        /*
        Get TNC Data From Server
         */
        presenter.getTNCData(PreferenceManager.getInstance(TermsAndConditionActivity.this).getCustomer().getToken());

    }

    @Override
    public void success(String response) {
        tncdata.setText(response);
    }

    @Override
    public void error(String response) {
        Snackbar.make(linearLayout, "" + response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void fail(String response) {
        Snackbar.make(linearLayout, "" + response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}
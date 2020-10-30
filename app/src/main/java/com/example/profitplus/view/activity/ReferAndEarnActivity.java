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
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.profitplus.BuildConfig;
import com.example.profitplus.R;
import com.example.profitplus.constant.PreferenceManager;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferAndEarnActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.drawerlayoutre)
    DrawerLayout drawerLayout;
    @BindView(R.id.re_list_menu_items)
    ListView list_menu;
    @BindView(R.id.re_toolBar)
    Toolbar toolbar;
    @BindView(R.id.iv_re_toggle)
    ImageView toggle;
    @BindView(R.id.tv_re_panding)
    TextView panding;
    @BindView(R.id.tv_re_total_earn)
    TextView totalEarn;
    @BindView(R.id.tv_re_user_number)
    TextView totalUser;
    @BindView(R.id.btn_share_card)
    CardView btnShare;

    String[] listItem;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_and_earn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorRed));
        }
        ButterKnife.bind(this);
        // Find Items
        initViews();

    }

    private void initViews() {
          /*
        Inialization of Navigation Items and toolbar
         */
        initNavigation();
    }

    private void initNavigation() {
        TextView nav_view_name;
        NavigationView navigationView = findViewById(R.id.nav_view_re);
        View header = navigationView.findViewById(R.id.navheader);
        nav_view_name = (TextView) header.findViewById(R.id.nav_view_user_name);
        nav_view_name.setText(PreferenceManager.getInstance(ReferAndEarnActivity.this).getCustomer().getfName() + " " + PreferenceManager.getInstance(ReferAndEarnActivity.this).getCustomer().getlName());

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        // Loding Menu items into Navigation View
        loadMenuItemList();

        // Click Events
        toggle.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==toggle){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        if(v==btnShare){
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Profit Plus");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            } catch(Exception e) {
                //e.toString();
            }
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
                    startActivity(new Intent(ReferAndEarnActivity.this, HomeActivity.class));
                    finish();
                }
                if (value.equals("Privacy Policy")) {
                    startActivity(new Intent(ReferAndEarnActivity.this, PrivacyPolicyActivity.class));
                    finish();
                }
                if (value.equals("Terms & Condition")) {
                    startActivity(new Intent(ReferAndEarnActivity.this, TermsAndConditionActivity.class));
                    finish();
                }
                if (value.equals("Refer & Earn")) {
                    startActivity(new Intent(ReferAndEarnActivity.this, ReferAndEarnActivity.class));
                    finish();
                }
                if (value.equals("FAQs")) {
                    startActivity(new Intent(ReferAndEarnActivity.this, FaqsActivity.class));
                    finish();
                }
                if (value.equals("My Account")) {
                    startActivity(new Intent(ReferAndEarnActivity.this, EditProfileActivity.class));
                }
                if (value.equals("Logout")) {
                    startActivity(new Intent(ReferAndEarnActivity.this, OnBoardActivity.class));
                    PreferenceManager.clear();
                    finish();
                }


            }
        });
    }


}
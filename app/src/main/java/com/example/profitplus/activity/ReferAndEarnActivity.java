package com.example.profitplus.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

import com.example.profitplus.R;

public class ReferAndEarnActivity extends AppCompatActivity {

    TextView menu_tv;
    String[] listItem;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    ListView list_menu;
    Toolbar toolbar;
    ImageView toggle;
    TextView panding,totalEarn,totalUser;
    CardView btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_and_earn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorRed));
        }

        // Find Items
        initViews();

    }

    private void initViews() {
        panding = findViewById(R.id.tv_re_panding);
        totalEarn = findViewById(R.id.tv_re_total_earn);
        totalUser = findViewById(R.id.tv_re_total_earn);
        btnShare = findViewById(R.id.btn_share_card);


        menu_tv = findViewById(R.id.tv_menu_item_list);
        toggle = findViewById(R.id.iv_re_toggle);
        toolbar = findViewById(R.id.re_toolBar);

        list_menu = findViewById(R.id.re_list_menu_items);
        drawerLayout = findViewById(R.id.drawerlayoutre);

          /*
        Inialization of Navigation Items and toolbar
         */
        initNavigation();
    }

    private void initNavigation() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        // Loding Menu items into Navigation View
        loadMenuItemList();

        // Click Events
        clickEvents();
    }

    private void clickEvents() {
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
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
                if(value.equals("Dashboard")){
                    startActivity(new Intent(ReferAndEarnActivity.this,HomeActivity.class));
                    finish();
                }
                if(value.equals("Privacy Policy")){
                    startActivity(new Intent(ReferAndEarnActivity.this,PrivacyPolicyActivity.class));
                    finish();
                }
                if(value.equals("Terms & Condition")){
                    startActivity(new Intent(ReferAndEarnActivity.this,TermsAndConditionActivity.class));
                    finish();
                }
                if(value.equals("Refer & Earn")){
                    startActivity(new Intent(ReferAndEarnActivity.this,ReferAndEarnActivity.class));
                    finish();
                }
                if(value.equals("FAQs")){
                    startActivity(new Intent(ReferAndEarnActivity.this,FaqsActivity.class));
                    finish();
                }


            }
        });
    }
}
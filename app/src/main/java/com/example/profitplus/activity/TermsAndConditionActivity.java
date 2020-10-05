package com.example.profitplus.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.Toast;

import com.example.profitplus.R;

public class TermsAndConditionActivity extends AppCompatActivity {
    TextView menu_tv;
    String[] listItem;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    ListView list_menu;
    Toolbar toolbar;
    ImageView toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorRed));
        }
        initNavigation();
    }

    private void initNavigation() {
        menu_tv = findViewById(R.id.tv_menu_item_list);
        toggle = findViewById(R.id.iv_tc_toggle);
        toolbar = findViewById(R.id.tc_toolBar);

        list_menu = findViewById(R.id.tc_list_menu_items);
        drawerLayout = findViewById(R.id.drawerlayouttc);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        loadMenuItemList();
        clickEvents();
    }

    private void clickEvents() {
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
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
                    startActivity(new Intent(TermsAndConditionActivity.this,HomeActivity.class));
                    finish();
                }
                if(value.equals("Privacy Policy")){
                    startActivity(new Intent(TermsAndConditionActivity.this,PrivacyPolicyActivity.class));
                    finish();
                }
                if(value.equals("Terms & Condition")){
                    startActivity(new Intent(TermsAndConditionActivity.this,TermsAndConditionActivity.class));
                    finish();
                }
                if(value.equals("Refer & Earn")){
                    startActivity(new Intent(TermsAndConditionActivity.this,ReferAndEarnActivity.class));
                    finish();
                }
                if(value.equals("FAQs")){
                    startActivity(new Intent(TermsAndConditionActivity.this,FaqsActivity.class));
                    finish();
                }


            }
        });
    }
}
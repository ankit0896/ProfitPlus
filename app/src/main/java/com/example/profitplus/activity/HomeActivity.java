package com.example.profitplus.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.profitplus.R;
import com.example.profitplus.adpater.HomePagerAdapter;
import com.example.profitplus.fragments.FundFragment;
import com.example.profitplus.fragments.OrderFragment;
import com.example.profitplus.fragments.PortfolioFragment;
import com.example.profitplus.fragments.ResearchFragment;
import com.example.profitplus.fragments.WatchListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {


    TextView menu_tv;
    String[] listItem;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    ListView list_menu;
    Toolbar toolbar;
    ImageView toggle;
    TabLayout tabLayout;
    ViewPager viewPager;
    HomePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorRed));
        }

        initViews();
    }

    private void initViews() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        menu_tv = findViewById(R.id.tv_menu_item_list);
        toggle = findViewById(R.id.iv_home_toggle);
        toolbar = findViewById(R.id.home_toolBar);
        list_menu = findViewById(R.id.home_list_menu_items);
        drawerLayout = findViewById(R.id.drawerlayouthome);


        initTabLayout();
        initNavigation();
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Watchlist"));
        tabLayout.addTab(tabLayout.newTab().setText("Portfolio"));
        tabLayout.addTab(tabLayout.newTab().setText("Order"));
        tabLayout.addTab(tabLayout.newTab().setText("Funds"));
        tabLayout.addTab(tabLayout.newTab().setText("Research"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        adapter = new HomePagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initNavigation() {

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
                if (value.equals("Dashboard")) {
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                    finish();
                }
                if (value.equals("Privacy Policy")) {
                    startActivity(new Intent(HomeActivity.this, PrivacyPolicyActivity.class));
                    finish();
                }
                if (value.equals("Terms & Condition")) {
                    startActivity(new Intent(HomeActivity.this, TermsAndConditionActivity.class));
                    finish();
                }
                if (value.equals("Refer & Earn")) {
                    startActivity(new Intent(HomeActivity.this, ReferAndEarnActivity.class));
                    finish();
                }
                if (value.equals("FAQs")) {
                    startActivity(new Intent(HomeActivity.this, FaqsActivity.class));
                    finish();
                }
            }
        });
    }

}
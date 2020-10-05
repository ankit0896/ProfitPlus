package com.example.profitplus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.profitplus.R;
import com.example.profitplus.adpater.ViewPagerAdapter;
import com.example.profitplus.model.OnBoardItem;

import java.util.ArrayList;

public class OnBoardActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout SliderDots;
    // private TextView register;
    private CardView btnlogin, cardRegister;
    ArrayList<OnBoardItem> list = new ArrayList<>();
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }

        setContentView(R.layout.activity_on_board);
        initView();

    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        SliderDots = findViewById(R.id.SliderDots);
        btnlogin = findViewById(R.id.cardLogin);
        cardRegister = findViewById(R.id.cardregister);

        loadData();
        clickEvents();

    }

    private void loadData() {
        list.add(new OnBoardItem(R.drawable.slider_1, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        list.add(new OnBoardItem(R.drawable.slider_2, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        list.add(new OnBoardItem(R.drawable.slider_3, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        list.add(new OnBoardItem(R.drawable.slider_4, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        list.add(new OnBoardItem(R.drawable.slider_5, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        setAdapter();
    }

    private void clickEvents() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardActivity.this, LoginActivity.class));
            }
        });

        cardRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardActivity.this, RegistrationActivity.class));
            }
        });
    }


    private void setAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(this, list);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.slider_non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(12, 0, 12, 0);

            SliderDots.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.slider_active_dot));
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < dotscount; i++) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.slider_non_active_dot));
            }

            dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.slider_active_dot));
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

}
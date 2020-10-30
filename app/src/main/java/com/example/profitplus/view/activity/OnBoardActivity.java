package com.example.profitplus.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.profitplus.R;
import com.example.profitplus.view.adpater.ViewPagerAdapter;
import com.example.profitplus.constant.PreferenceManager;
import com.example.profitplus.model.OnBoardItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnBoardActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.SliderDots) LinearLayout SliderDots;
    @BindView(R.id.cardLogin) CardView btnlogin;
    @BindView(R.id.cardregister) CardView cardRegister;

    ArrayList<OnBoardItem> list = new ArrayList<>();
    private int dotscount;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView[] dots;
    PreferenceManager preferenceManager;

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
        preferenceManager = new PreferenceManager(OnBoardActivity.this);
        if(PreferenceManager.getInstance(OnBoardActivity.this).getCustomer().getLoginStatus()){
            startActivity(new Intent(OnBoardActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }else{
            // Initialization Of View or View Group
            ButterKnife.bind(this);
            initView();
        }
    }

    private void initView() {
        loadData();
        btnlogin.setOnClickListener(this);
        cardRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnlogin){
            startActivity(new Intent(OnBoardActivity.this, LoginActivity.class));
        }
        if(v==cardRegister){
            startActivity(new Intent(OnBoardActivity.this, RegistrationActivity.class));

        }
    }

    private void loadData() {
        list.add(new OnBoardItem(R.drawable.slider_1, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        list.add(new OnBoardItem(R.drawable.slider_2, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        list.add(new OnBoardItem(R.drawable.slider_3, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        list.add(new OnBoardItem(R.drawable.slider_4, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        list.add(new OnBoardItem(R.drawable.slider_5, "Phasellus viverra dolor at cursus congue", "Suspendisse potenti. Curabitur venenatis elementum elit, ut congue"));
        setAdapter();
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
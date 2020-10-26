package com.example.profitplus.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.profitplus.R;
import com.example.profitplus.model.OnBoardItem;

import java.util.ArrayList;


public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<OnBoardItem> onBoardItems = new ArrayList<>();

    public ViewPagerAdapter(Context context, ArrayList<OnBoardItem> onBoardItems) {
        this.context = context;
        this.onBoardItems = onBoardItems;
    }

    @Override
    public int getCount() {
        return onBoardItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.onboard_layout,null);
        TextView title,desc;
        ImageView iv;
        title = view.findViewById(R.id.tv_title);
        title.setText(onBoardItems.get(position).getTitle());
        desc = view.findViewById(R.id.tv_desc);
        desc.setText(onBoardItems.get(position).getDescription());
        iv = view.findViewById(R.id.iv_onboard);
        iv.setImageResource(onBoardItems.get(position).getImageId());
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}

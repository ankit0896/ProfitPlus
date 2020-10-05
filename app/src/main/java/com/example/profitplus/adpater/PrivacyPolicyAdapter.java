package com.example.profitplus.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profitplus.R;

import java.util.ArrayList;
import java.util.List;

public class PrivacyPolicyAdapter extends RecyclerView.Adapter<PrivacyPolicyAdapter.PrivacyPolicyViewHolder> {

    Context context;
    List<String> list = new ArrayList<>();
    OnItemClick onItemClick;

    public PrivacyPolicyAdapter(Context context, List<String> list, OnItemClick onItemClick) {
        this.context = context;
        this.list = list;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public PrivacyPolicyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_privacy_policy_layout, parent, false);
        return new PrivacyPolicyViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull PrivacyPolicyViewHolder holder, final int position) {
            holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PrivacyPolicyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public PrivacyPolicyViewHolder(@NonNull View itemView, final OnItemClick onItemClick) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_privacy_policy);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClick{
        void onItemClick(int position);
    }
}

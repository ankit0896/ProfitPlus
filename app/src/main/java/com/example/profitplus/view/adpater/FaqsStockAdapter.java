package com.example.profitplus.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profitplus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqsStockAdapter extends RecyclerView.Adapter<FaqsStockAdapter.MyFaqsStockViewHolder> {

    OnFaqsStockQuestionListner onFaqsStockQuestionListner;
    Context context;


    @NonNull
    @Override
    public MyFaqsStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_faqs_stock_item,parent,false);
        return new MyFaqsStockViewHolder(view,onFaqsStockQuestionListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFaqsStockViewHolder holder, int position) {
        holder.questions.setText("");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyFaqsStockViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_faqs_stock_questions)
        TextView questions;
        @BindView(R.id.iv_faqs_stock_image)
        ImageView rightarrow;
        public MyFaqsStockViewHolder(@NonNull View itemView, OnFaqsStockQuestionListner onFaqsStockQuestionListner) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnFaqsStockQuestionListner{
        void onQuestionClick(int postion);
    }
}

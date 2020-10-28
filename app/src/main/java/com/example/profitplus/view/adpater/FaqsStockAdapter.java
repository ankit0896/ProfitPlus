package com.example.profitplus.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profitplus.R;
import com.example.profitplus.model.faqstockmodel.StockFaq;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqsStockAdapter extends RecyclerView.Adapter<FaqsStockAdapter.MyFaqsStockViewHolder> {
    List<StockFaq>stockFaqList;
    Context context;
    int visibilitycount=0;
    public FaqsStockAdapter( Context context,List<StockFaq> stockFaqList) {
        this.stockFaqList = stockFaqList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyFaqsStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_faqs_stock_item,parent,false);
        return new MyFaqsStockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFaqsStockViewHolder holder, final int position) {
        StockFaq stockFaq=stockFaqList.get(position);
        holder.questions.setText(stockFaq.getQuestion());
        holder.answer.setText(stockFaq.getAnswer());
        holder.btnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visibilitycount==0){
                    holder.answer.setVisibility(View.GONE);
                    visibilitycount=1;
                }
                else{
                    holder.answer.setVisibility(View.VISIBLE);
                    visibilitycount=0;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return stockFaqList.size();
    }

    public class MyFaqsStockViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_faqs_stock_questions)
        TextView questions;
        @BindView(R.id.tv_faqs_stock_answer)
        TextView answer;
        @BindView(R.id.iv_faqs_stock_image)
        ImageView rightarrow;
        @BindView(R.id.btnly)
        RelativeLayout btnly;
        public MyFaqsStockViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}

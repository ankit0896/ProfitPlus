package com.example.profitplus.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.profitplus.R;
import com.example.profitplus.view.adpater.FaqsStockAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockFragment extends Fragment implements FaqsStockAdapter.OnFaqsStockQuestionListner {



    @BindView(R.id.rv_faqs_questions)
    RecyclerView rv_question_list;
    @BindView(R.id.tv_faqs_QnAs)
    TextView answer;

    FaqsStockAdapter faqsStockAdapter;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stock, container, false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        faqsStockAdapter = new FaqsStockAdapter();
        rv_question_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_question_list.setAdapter(faqsStockAdapter);
    }

    @Override
    public void onQuestionClick(int postion) {

        answer.setText("");
    }
}
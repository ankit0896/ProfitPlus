package com.example.profitplus.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.profitplus.R;
import com.example.profitplus.constant.PreferenceManager;
import com.example.profitplus.model.faqstockmodel.StockFaq;
import com.example.profitplus.presenter.FAQPresenter;
import com.example.profitplus.view.activity.FaqsActivity;
import com.example.profitplus.view.activity.PrivacyPolicyActivity;
import com.example.profitplus.view.adpater.FaqsStockAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockFragment extends Fragment implements FAQPresenter.FAQPre {
    @BindView(R.id.rv_faqs_questions)
    RecyclerView rv_question_list;
    @BindView(R.id.alert)
    RelativeLayout relativeLayout;
    FaqsStockAdapter faqsStockAdapter;
    View view;
    private FAQPresenter presenter;
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
        presenter=new FAQPresenter(getContext(), StockFragment.this);
        rv_question_list.setHasFixedSize(true);
        rv_question_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        presenter.GetFaq(PreferenceManager.getInstance(getContext()).getCustomer().getToken());
    }

    @Override
    public void success(List<StockFaq> faqArrayList) {
        faqsStockAdapter = new FaqsStockAdapter(getContext(),faqArrayList);
        rv_question_list.setAdapter(faqsStockAdapter);
        faqsStockAdapter.notifyDataSetChanged();

    }

    @Override
    public void error(String response) {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, response, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void fail(String response) {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, response, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
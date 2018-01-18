package com.blockchain.ui.chart.martketprice;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blockchain.R;
import com.blockchain.ui.base.BaseFragment;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

import javax.inject.Inject;

public class MarketPriceChartFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    LineChart chart;

    @Inject
    public MarketPriceChartFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart_market_price;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MarketPriceViewModel viewModel = ViewModelProviders
                .of(this, viewModelFactory).get(MarketPriceViewModel.class);
        viewModel.marketPriceLiveData().observe(this, this::updateChart);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart = view.findViewById(R.id.chart);
    }

    private void updateChart(List<MarketPriceEntity> marketPriceEntities) {
    }
}

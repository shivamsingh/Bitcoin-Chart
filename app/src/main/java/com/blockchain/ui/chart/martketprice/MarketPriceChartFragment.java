package com.blockchain.ui.chart.martketprice;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.blockchain.R;
import com.blockchain.ui.base.BaseFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
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
        setupChart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart = view.findViewById(R.id.chart);
    }

    private void setupChart() {
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new DateAxisValueFormatter(chart));
    }

    private void updateChart(@NonNull final List<MarketPriceEntity> marketPriceEntities) {
        List<Entry> values = new ArrayList<>();
        for (MarketPriceEntity marketPriceEntity : marketPriceEntities)
            values.add(new Entry(marketPriceEntity.dateInMs(), marketPriceEntity.price()));
        chart.setData(new LineData(marketPriceDataSet(values)));
        chart.invalidate();
    }

    @NonNull
    private LineDataSet marketPriceDataSet(@NonNull final List<Entry> values) {
        LineDataSet marketPriceDataSet = new LineDataSet(values, "Market Price (USD)");
        marketPriceDataSet.setDrawIcons(false);
        marketPriceDataSet.setColor(Color.BLACK);
        marketPriceDataSet.setCircleColor(Color.BLACK);
        marketPriceDataSet.setCircleColorHole(Color.BLACK);
        marketPriceDataSet.setCircleHoleRadius(0.5f);
        marketPriceDataSet.setCircleRadius(1f);
        marketPriceDataSet.setLineWidth(1f);
        marketPriceDataSet.setDrawFilled(false);
        marketPriceDataSet.setFormLineWidth(1f);
        marketPriceDataSet.setFormSize(15.f);
        return marketPriceDataSet;
    }
}

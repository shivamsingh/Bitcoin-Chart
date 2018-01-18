package com.blockchain.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blockchain.R;
import com.blockchain.ui.base.BaseActivity;
import com.blockchain.ui.chart.martketprice.MarketPriceChartFragment;

import javax.inject.Inject;

import dagger.Lazy;
import polanski.option.Option;

import static com.blockchain.utils.ActivityUtils.addFragmentToActivity;

public class HomeActivity extends BaseActivity {

    @Inject
    Lazy<MarketPriceChartFragment> marketPriceChartFragmentLazy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Option.ofObj(savedInstanceState).ifNone(this::showMarketPriceChart);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    private void showMarketPriceChart() {
        addFragmentToActivity(getSupportFragmentManager(), marketPriceChartFragmentLazy.get(), R.id.container);
    }
}

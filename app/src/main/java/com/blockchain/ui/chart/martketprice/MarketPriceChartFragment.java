package com.blockchain.ui.chart.martketprice;

import com.blockchain.R;
import com.blockchain.ui.base.BaseFragment;

import javax.inject.Inject;

public class MarketPriceChartFragment extends BaseFragment {

    @Inject
    public MarketPriceChartFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart_market_price;
    }
}

package com.blockchain.ui.chart.martketprice;

import com.blockchain.data.chart.marketprice.MarketPrice;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class MarketPriceEntityMapper implements Function<MarketPrice, MarketPriceEntity> {

    @Inject
    public MarketPriceEntityMapper() {
    }

    @Override
    public MarketPriceEntity apply(MarketPrice marketPrice) throws Exception {
        return null;
    }
}

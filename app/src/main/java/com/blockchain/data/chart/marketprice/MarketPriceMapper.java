package com.blockchain.data.chart.marketprice;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class MarketPriceMapper implements Function<MarketPriceRaw, MarketPrice> {

    private static final String TAG = MarketPriceMapper.class.getSimpleName();

    @Inject
    MarketPriceMapper() {
    }

    @Override
    public MarketPrice apply(MarketPriceRaw marketPriceRaw) throws Exception {
        return MarketPrice.builder()
                .price(marketPriceRaw.y())
                //Convert EPOCH time to current milliseconds
                .dateInMs(marketPriceRaw.x() * 1000)
                .build();
    }
}

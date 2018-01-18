package com.blockchain.ui.chart.martketprice;

import com.blockchain.data.chart.marketprice.MarketPrice;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MarketPriceEntityMapper implements Function<List<MarketPrice>, List<MarketPriceEntity>> {

    @Override
    public List<MarketPriceEntity> apply(List<MarketPrice> marketPrices) throws Exception {
        return Observable
                .fromIterable(marketPrices)
                .map(new MarketPriceEntityItemMapper())
                .toList()
                .blockingGet();
    }

    class MarketPriceEntityItemMapper implements Function<MarketPrice, MarketPriceEntity> {

        @Override
        public MarketPriceEntity apply(MarketPrice marketPrice) throws Exception {
            return null;
        }
    }
}

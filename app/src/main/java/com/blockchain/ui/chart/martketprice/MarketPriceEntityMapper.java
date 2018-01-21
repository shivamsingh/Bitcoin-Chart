package com.blockchain.ui.chart.martketprice;

import com.blockchain.data.chart.marketprice.MarketPrice;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MarketPriceEntityMapper implements Function<List<MarketPrice>, List<MarketPriceEntity>> {

    @Inject
    public MarketPriceEntityMapper() {
    }

    public MarketPriceEntity map(MarketPrice marketPrice) throws Exception {
        return MarketPriceEntity.builder().price((float) marketPrice.price()).dateInMs(marketPrice.dateInMs()).build();
    }

    @Override
    public List<MarketPriceEntity> apply(List<MarketPrice> marketPrices) throws Exception {
        return Observable
                .fromIterable(marketPrices).map(this::map)
                .toSortedList((mp1, mp2) -> mp1.dateInMs() > mp2.dateInMs() ? 1 : -1)
                .blockingGet();
    }


}

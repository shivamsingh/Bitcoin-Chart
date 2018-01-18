package com.blockchain.data.chart.marketprice;

import com.blockchain.data.chart.marketprice.MarketPrice;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import polanski.option.Option;

public interface ChartRepository {

    Observable<Option<List<MarketPrice>>> getMarketPrice();

    Completable fetchMarketPrice();
}

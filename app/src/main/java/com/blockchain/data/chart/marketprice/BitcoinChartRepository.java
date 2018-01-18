package com.blockchain.data.chart.marketprice;

import com.blockchain.data.chart.ChartResponseHolder;
import com.blockchain.data.store.ReactiveStore;
import com.blockchain.network.ChartService;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import polanski.option.Option;

public class BitcoinChartRepository implements ChartRepository {

    @Nonnull
    private final ReactiveStore<String, MarketPrice> store;

    @Nonnull
    private final ChartService chartService;

    @Nonnull
    private final MarketPriceMapper marketPriceMapper;

    @Inject
    BitcoinChartRepository(@Nonnull ReactiveStore<String, MarketPrice> store,
                           @Nonnull ChartService chartService,
                           @Nonnull MarketPriceMapper marketPriceMapper) {
        this.store = store;
        this.chartService = chartService;
        this.marketPriceMapper = marketPriceMapper;
    }

    @Override
    public Observable<Option<List<MarketPrice>>> getMarketPrice() {
        return store.getAll();
    }

    @Override
    public Completable fetchMarketPrice() {
        return chartService.marketPrice()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(ChartResponseHolder::response)
                .flatMapObservable(Observable::fromIterable)
                .map(marketPriceMapper)
                .toList()
                .doOnSuccess(store::putAll)
                .toCompletable();
    }
}

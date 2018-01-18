package com.blockchain.domain.chart.marketprice;

import com.blockchain.common.rx.UnwrapOptionTransformer;
import com.blockchain.data.chart.marketprice.MarketPrice;
import com.blockchain.domain.ReactiveInteractor;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import polanski.option.Option;

import static io.reactivex.Single.just;

public class RetrieveMarketPriceList implements ReactiveInteractor.RetrieveInteractor<Void, List<MarketPrice>> {

    @Nonnull
    private final ChartRepository chartRepository;

    @Inject
    public RetrieveMarketPriceList(@Nonnull ChartRepository chartRepository) {
        this.chartRepository = chartRepository;
    }

    @Nonnull
    @Override
    public Observable<List<MarketPrice>> getBehaviourStream(@Nonnull Option<Void> params) {
        return chartRepository.getMarketPrice()
                .flatMapSingle(this::fetchWhenNoneAndThenDrafts).compose(UnwrapOptionTransformer.create());
    }

    private Single<Option<List<MarketPrice>>> fetchWhenNoneAndThenDrafts(Option<List<MarketPrice>> marketPrices) {
        return fetchWhenNone(marketPrices).andThen(just(marketPrices));
    }

    private Completable fetchWhenNone(Option<List<MarketPrice>> marketPrices) {
        return marketPrices.isNone() ? chartRepository.fetchMarketPrice() : Completable.complete();
    }
}

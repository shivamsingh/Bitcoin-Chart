package com.blockchain.ui.chart.martketprice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.blockchain.domain.chart.marketprice.RetrieveMarketPriceList;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import polanski.option.Option;

public class MarketPriceViewModel extends ViewModel {

    private static final String TAG = MarketPriceViewModel.class.getSimpleName();

    @Nonnull
    private final RetrieveMarketPriceList retrieveMarketPriceList;

    @NonNull
    private final MarketPriceEntityMapper marketPriceEntityMapper;

    @NonNull
    private final MutableLiveData<List<MarketPriceEntity>> marketPriceListLiveData = new MutableLiveData<>();

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    MarketPriceViewModel(@Nonnull final RetrieveMarketPriceList retrieveMarketPriceList,
                         @NonNull final MarketPriceEntityMapper marketPriceEntityMapper) {
        this.retrieveMarketPriceList = retrieveMarketPriceList;
        this.marketPriceEntityMapper = marketPriceEntityMapper;

        compositeDisposable.add(bindToMarketPrice());
    }

    @NonNull
    public LiveData<List<MarketPriceEntity>> marketPriceLiveData() {
        return marketPriceListLiveData;
    }

    private Disposable bindToMarketPrice() {
        return retrieveMarketPriceList
                .getBehaviourStream(Option.none())
                .observeOn(Schedulers.computation())
                .map(marketPriceEntityMapper)
                .subscribe(marketPriceListLiveData::postValue,
                        e -> Log.e(TAG, "Error updating market price list live data", e));
    }
}

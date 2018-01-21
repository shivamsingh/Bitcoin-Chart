package com.blockchain.data.chart.marketprice;

import com.blockchain.common.providers.TimestampProvider;
import com.blockchain.data.cache.Cache;
import com.blockchain.data.store.MemoryReactiveStore;
import com.blockchain.data.store.ReactiveStore;
import com.blockchain.data.store.Store;
import com.blockchain.network.ChartService;
import com.google.gson.TypeAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import retrofit2.Retrofit;

@Module
public class ChartMarketPriceDataModule {
    private static long CACHE_MAX_AGE = 5 * 60 * 1000; // 5 minutes

    @Provides
    @IntoSet
    TypeAdapterFactory provideTypeAdapterFactory() {
        return MarketPriceTypeAdapterFactory.create();
    }

    @Provides
    ChartService chartService(Retrofit retrofit) {
        return retrofit.create(ChartService.class);
    }

    @Singleton
    @Provides
    Store.MemoryStore<String, MarketPrice> cache(TimestampProvider timestampProvider) {
        return new Cache<>(marketPrice -> String.valueOf(marketPrice.dateInMs()), timestampProvider, CACHE_MAX_AGE);
    }

    @Singleton
    @Provides
    ReactiveStore<String, MarketPrice> reactiveStore(Store.MemoryStore<String, MarketPrice> cache) {
        return new MemoryReactiveStore<>(cache, marketPrice -> String.valueOf(marketPrice.dateInMs()));
    }
}

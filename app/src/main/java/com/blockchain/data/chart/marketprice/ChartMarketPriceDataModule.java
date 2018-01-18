package com.blockchain.data.chart.marketprice;

import com.blockchain.network.ChartService;
import com.google.gson.TypeAdapterFactory;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import retrofit2.Retrofit;

@Module
public class ChartMarketPriceDataModule {

    @Provides
    @IntoSet
    TypeAdapterFactory provideTypeAdapterFactory() {
        return MarketPriceTypeAdapterFactory.create();
    }

    @Provides
    ChartService chartService(Retrofit retrofit) {
        return retrofit.create(ChartService.class);
    }


}

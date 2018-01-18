package com.blockchain.di.module;

import com.blockchain.data.chart.marketprice.ChartMarketPriceDataModule;

import dagger.Module;

@Module(includes = {ChartMarketPriceDataModule.class})
public final class DataModule {
}

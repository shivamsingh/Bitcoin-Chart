package com.blockchain.ui.chart.martketprice;

import com.blockchain.di.scope.ActivityScope;
import com.blockchain.di.scope.FragmentScope;
import com.blockchain.domain.chart.marketprice.BitcoinChartRepository;
import com.blockchain.domain.chart.marketprice.ChartRepository;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MarketPriceModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract MarketPriceChartFragment marketPriceChartFragment();

    @ActivityScope
    @Binds
    abstract ChartRepository chartRepository(BitcoinChartRepository chartRepository);
}

package com.blockchain.ui.chart.martketprice;

import android.arch.lifecycle.ViewModelProvider;

import com.blockchain.ui.util.ViewModelUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class MarketPriceViewModelModule {

    @Singleton
    @Provides
    static ViewModelProvider.Factory viewModelProviderFactory(ViewModelUtil viewModelUtil,
                                                              MarketPriceViewModel viewModel) {
        return viewModelUtil.createFor(viewModel);
    }
}

package com.blockchain.di.module;

import com.blockchain.di.scope.ActivityScope;
import com.blockchain.ui.chart.martketprice.MarketPriceModule;
import com.blockchain.ui.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MarketPriceModule.class})
    abstract HomeActivity homeActivity();
}

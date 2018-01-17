package com.blockchain.di;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BlockchainApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}

package com.blockchain.di;

import android.app.Application;

import com.blockchain.BlockchainApplication;
import com.blockchain.di.module.ActivityBindingModule;
import com.blockchain.di.module.ApplicationModule;
import com.blockchain.di.module.DataModule;
import com.blockchain.di.module.NetworkModule;
import com.blockchain.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        ApplicationModule.class, NetworkModule.class, ActivityBindingModule.class,
        AndroidSupportInjectionModule.class, DataModule.class, ViewModelModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(BlockchainApplication blockchainApplicationa);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}

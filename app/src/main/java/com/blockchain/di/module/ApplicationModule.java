package com.blockchain.di.module;

import android.app.Application;
import android.content.Context;

import com.blockchain.di.qualifiers.ForApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @ForApplication
    @Provides
    Context provideApplicationContext(Application app) {
        return app.getApplicationContext();
    }
}

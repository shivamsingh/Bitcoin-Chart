package com.blockchain.di.module;

import android.content.Context;
import android.util.Log;

import com.blockchain.di.qualifiers.ForApplication;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
class InstrumentationModule {

    @Provides
    @NetworkModule.NetworkInterceptor
    @IntoSet
    @Singleton
    static HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.d("OkHttp", message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @NetworkModule.NetworkInterceptor
    @IntoSet
    @Singleton
    static StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @NetworkModule.AppInterceptor
    @IntoSet
    @Singleton
    static ChuckInterceptor provideChuckInterceptor(@ForApplication Context context) {
        return new ChuckInterceptor(context);
    }
}
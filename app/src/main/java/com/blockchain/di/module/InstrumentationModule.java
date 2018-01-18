package com.blockchain.di.module;

import android.content.Context;
import android.util.Log;

import com.blockchain.di.module.NetworkModule.AppInterceptor;
import com.blockchain.di.module.NetworkModule.NetworkInterceptor;
import com.blockchain.di.qualifiers.ForApplication;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class InstrumentationModule {

    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    static Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.d("OkHttp", message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    static Interceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @AppInterceptor
    @IntoSet
    @Singleton
    static Interceptor provideChuckInterceptor(@ForApplication Context context) {
        return new ChuckInterceptor(context);
    }
}
package com.blockchain.di.module;

import com.blockchain.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = InstrumentationModule.class)
public class NetworkModule {
    private static final String API_URL = "API_URL";

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface AppInterceptor {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface NetworkInterceptor {
    }

    @Provides
    @Named(API_URL)
    static String provideBaseUrl() {
        return BuildConfig.BLOCKCHAIN_API_URL;
    }

    @Provides
    @Singleton
    static Gson provideGson(Set<TypeAdapterFactory> typeAdapters) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    protected Retrofit retrofit(@Named(API_URL) String apiUrl, Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(apiUrl)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    static OkHttpClient provideApiOkHttpClient(
            @AppInterceptor Set<Interceptor> appInterceptor,
            @NetworkInterceptor Set<Interceptor> networkInterceptor) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.interceptors().addAll(appInterceptor);
        okBuilder.networkInterceptors().addAll(networkInterceptor);

        return okBuilder.build();
    }
}

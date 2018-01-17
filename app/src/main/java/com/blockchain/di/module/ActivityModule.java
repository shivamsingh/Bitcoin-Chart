package com.blockchain.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.blockchain.di.qualifiers.ForActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @NonNull
    private final Activity activity;

    public ActivityModule(@NonNull final Activity activity) {
        this.activity = activity;
    }

    @ForActivity
    @Provides
    Context provideContext() {
        return activity;
    }

    @Provides
    FragmentManager provideFragmentManager(@NonNull final AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}

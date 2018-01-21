package com.blockchain.ui.chart.martketprice;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MarketPriceEntity {


    abstract float price();

    abstract long dateInMs();

    @NonNull
    public static MarketPriceEntity.Builder builder() {
        return new AutoValue_MarketPriceEntity.Builder();
    }

    @AutoValue.Builder
    interface Builder {

        MarketPriceEntity.Builder price(final float price);

        MarketPriceEntity.Builder dateInMs(final long dateInMs);

        MarketPriceEntity build();
    }
}

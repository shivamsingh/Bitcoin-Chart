package com.blockchain.data.chart.marketprice;

import com.google.auto.value.AutoValue;

import javax.annotation.Nonnull;

@AutoValue
public abstract class MarketPrice {

    public abstract long dateInMs();

    public abstract double price();

    @Nonnull
    public static MarketPrice.Builder builder() {
        return new AutoValue_MarketPrice.Builder();
    }

    @AutoValue.Builder
    interface Builder {

        MarketPrice.Builder dateInMs(final long dateInMs);

        MarketPrice.Builder price(final double price);

        MarketPrice build();
    }
}

package com.blockchain.data.chart.marketprice;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class MarketPriceRaw {

    abstract long x();

    abstract float and();

    public static TypeAdapter<MarketPriceRaw> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_MarketPriceRaw.GsonTypeAdapter(gson);
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_MarketPriceRaw.Builder();
    }

    @AutoValue.Builder
    interface Builder {

        Builder x(final long x);

        Builder and(final float and);

        MarketPriceRaw build();
    }
}

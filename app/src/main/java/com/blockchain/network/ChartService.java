package com.blockchain.network;

import com.blockchain.data.chart.ChartResponseHolder;
import com.blockchain.data.chart.marketprice.MarketPriceRaw;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ChartService {

    @GET("charts/market-price?format=json")
    Single<ChartResponseHolder<List<MarketPriceRaw>>> marketPrice();
}

package com.example.typsy.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gravity on 10/18/17.
 */

public interface ApiService {

    @GET("/data/pricemultifull")
    Observable<PriceResponse> getPriceResponse(@Query(value = "fsyms", encoded = true) String fromCoin, @Query(value = "tsyms", encoded = true) String toCurrency);

}


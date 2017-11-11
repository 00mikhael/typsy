package com.example.typsy.data.network;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gravity on 10/21/17.
 */
// Retrofit client class
public class ApiClient {

    private static ApiClient instance;
    private ApiService apiService;

    private ApiClient(String baseUrl) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiClient getInstance(String baseUrl) {
        if (instance == null) {
            instance = new ApiClient(baseUrl);
        }
        return instance;
    }

    // price call
    public Observable<PriceResponse> getPriceResponse(@NonNull String fsym, String tsym) {
        return apiService.getPriceResponse(fsym, tsym);
    }
}

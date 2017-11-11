package com.example.typsy.data.network;

import android.support.annotation.NonNull;

import com.example.typsy.AppConstant;
import com.example.typsy.data.Callback;
import com.example.typsy.data.local.entity.Price;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gravity on 10/30/17.
 */
/**
 * Remote Data Source - A Singleton class that Abstracts
 * away how data is fetched from the network from the data controller
 */
public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    private RemoteDataSource() {
    }

    public void getPriceResponse(@NonNull String fsym, @NonNull String tsym, Callback.ApiCallbak callbak) {
        ApiClient.getInstance(AppConstant.BASE_URL_ONE)
                .getPriceResponse(fsym, tsym)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PriceResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull PriceResponse priceResponse) {
                        Price price = new Price();
                        Map<String, Map<String, PriceRaw>> outerMapRaw = priceResponse.getRaw();
                        Map<String, Map<String, PriceDisplay>> outerMapDisplay = priceResponse.getDisplay();
                        Map<String, PriceRaw> innerMapRaw = new HashMap<String, PriceRaw>();
                        Map<String, PriceDisplay> innerMapDisplay = new HashMap<String, PriceDisplay>();
                        for (Map.Entry<String, Map<String, PriceRaw>> outerEntry : outerMapRaw.entrySet()) {
                            innerMapRaw = outerEntry.getValue();
                        }
                        PriceRaw priceRaw;
                        for (Map.Entry<String, PriceRaw> innerEntry : innerMapRaw.entrySet()) {
                            priceRaw = innerEntry.getValue();
                            price.setfCode(priceRaw.getFsym());
                            price.settCode(priceRaw.getTsym());
                            price.setRawPrice(priceRaw.getPrice());
                        }

                        for (Map.Entry<String, Map<String, PriceDisplay>> outerEntry : outerMapDisplay.entrySet()) {
                            innerMapDisplay = outerEntry.getValue();
                        }
                        PriceDisplay priceDisplay;
                        for (Map.Entry<String, PriceDisplay> innerEntry : innerMapDisplay.entrySet()) {
                            priceDisplay = innerEntry.getValue();
                            price.setfSym(priceDisplay.getFsym());
                            price.settSym(priceDisplay.getTsym());
                            price.setDisplayPrice(priceDisplay.getPrice());
                            callbak.onApiResponse(price);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        callbak.apiError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}

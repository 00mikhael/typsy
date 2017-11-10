package com.example.typsy.data.local;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.typsy.data.Callback;
import com.example.typsy.data.ReactiveStreams;
import com.example.typsy.data.local.dao.ConversionDao;
import com.example.typsy.data.local.dao.PriceDao;
import com.example.typsy.data.local.entity.Conversion;
import com.example.typsy.data.local.entity.Price;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gravity on 10/30/17.
 */

public class LocalDataSource {

    private static LocalDataSource INSTANCE;

    private ConversionDao mConversionDao;
    private PriceDao mPriceDao;

    public static LocalDataSource getInstance(AppDatabase database) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(database);
        }
        return INSTANCE;
    }

    private LocalDataSource(AppDatabase database) {
        mConversionDao = database.conversionDao();
        mPriceDao = database.priceDao();
    }


    public void savePrice(Price price) {
        Single.just(price)
                .subscribeOn(Schedulers.io())
                .subscribe(price1 -> mPriceDao.insertPrice(price1));
    }


    public void getPrice(String fsym, String tsym, Callback.DBCallbak callbak) {
        mPriceDao.getPrice(fsym, tsym)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<Price>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Price price) {
                        callbak.initialData(price);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbak.dbError(e);
                    }

                    @Override
                    public void onComplete() {
                        callbak.onComplete();
                    }
                });

    }

    public LiveData<Conversion> getConversion(@NonNull String id) {
        return new ReactiveStreams<Conversion>().toLiveData(mConversionDao.getConversion(id));
    }

    public LiveData<List<Conversion>> getConversionList() {
        return new ReactiveStreams<List<Conversion>>().toLiveData(mConversionDao.getConversionList());
    }

    public void saveConversionList(List<Conversion> conversions) {
        Single.just(conversions)
                .subscribeOn(Schedulers.io())
                .subscribe(conversions1 -> mConversionDao.insertConversionList(conversions1));
    }

    public void saveConversion(Conversion conversion) {
        Single.just(conversion)
                .subscribeOn(Schedulers.io())
                .subscribe(conversion1 -> mConversionDao.insertConversion(conversion1));
    }

    public void updateConversion(Conversion conversion) {
        Single.just(conversion)
                .subscribeOn(Schedulers.io())
                .subscribe(conversion1 -> mConversionDao.updateConversion(conversion1));
    }

    public void deleteConversion(Conversion conversion) {
        Single.just(conversion)
                .subscribeOn(Schedulers.io())
                .subscribe(conversion1 -> mConversionDao.deleteConversion(conversion1));
    }
}

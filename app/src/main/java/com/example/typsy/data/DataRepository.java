package com.example.typsy.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.typsy.data.local.AppDatabase;
import com.example.typsy.data.local.LocalDataSource;
import com.example.typsy.data.local.entity.Conversion;
import com.example.typsy.data.local.entity.Price;
import com.example.typsy.data.network.RemoteDataSource;

import java.util.List;

/**
 * Created by gravity on 10/20/17.
 */

public class DataRepository implements RepositoryInterface {

    private static DataRepository INSTANCE;
    private LocalDataSource mLocalSource;
    private RemoteDataSource mRemoteSource;


    public static DataRepository getInstance(AppDatabase database) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(database);
        }
        return INSTANCE;
    }

    private DataRepository(AppDatabase database) {
        mLocalSource = LocalDataSource.getInstance(database);
        mRemoteSource = RemoteDataSource.getInstance();
    }

    @Override
    public void getPriceResponse(@NonNull String fsym, @NonNull String tsym,
                                            Callback.VmCallback callback) {
        new PriceSource(mLocalSource, mRemoteSource, fsym, tsym) {
            @Override
            protected void onResponse(Resource<Price> resource) {
                callback.onResponse(resource);
            }

            @Override
            protected void onErr(Resource<Price> resource) {
                callback.onResponse(resource);
            }

            @Override
            protected void onFinish() {

            }
        };
    }

    @Override
    public LiveData<Conversion> getConversion(@NonNull String id) {
        return mLocalSource.getConversion(id);
    }

    @Override
    public LiveData<List<Conversion>> getConversionList() {
        return mLocalSource.getConversionList();
    }

    @Override
    public void saveConversionList(List<Conversion> conversions) {
        mLocalSource.saveConversionList(conversions);
    }

    @Override
    public void saveConversion(Conversion conversion) {
        mLocalSource.saveConversion(conversion);
    }

    @Override
    public void updateConversion(Conversion conversion) {
        mLocalSource.updateConversion(conversion);
    }

    @Override
    public void deleteConversion(Conversion conversion) {
        mLocalSource.deleteConversion(conversion);
    }
}

package com.example.typsy.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.typsy.data.local.entity.Conversion;

import java.util.List;

/**
 * Created by gravity on 10/20/17.
 */
public interface RepositoryInterface {

    void getPriceResponse(@NonNull String fsym, String tsym, Callback.VmCallback callback);

    LiveData<Conversion> getConversion(@NonNull String id);

    LiveData<List<Conversion>> getConversionList();

    void saveConversionList(List<Conversion> conversions);

    void saveConversion(Conversion conversion);

    void updateConversion(Conversion conversion);

    void deleteConversion(Conversion conversion);

}

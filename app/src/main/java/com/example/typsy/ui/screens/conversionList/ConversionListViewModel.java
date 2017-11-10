package com.example.typsy.ui.screens.conversionList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.example.typsy.data.DataRepository;
import com.example.typsy.data.local.AppDatabase;
import com.example.typsy.data.local.entity.Conversion;

import java.util.List;

/**
 * Created by gravity on 11/7/17.
 */

public class ConversionListViewModel extends AndroidViewModel {

    private LiveData<List<Conversion>> conversionList = new MediatorLiveData<>();

    private DataRepository mRepository;

    public ConversionListViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        mRepository = DataRepository.getInstance(database);
        conversionList = mRepository.getConversionList();
    }

    public LiveData<List<Conversion>> getConversionList() {
        return conversionList;
    }

    public void deleteConversion(Conversion conversion) {
        mRepository.deleteConversion(conversion);
    }

    public void saveConversionList(List<Conversion> conversions) {
        mRepository.saveConversionList(conversions);
    }
}

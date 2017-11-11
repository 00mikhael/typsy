package com.example.typsy.ui.screens.conversionDetail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.typsy.AppConstant;
import com.example.typsy.data.DataRepository;
import com.example.typsy.data.Resource;
import com.example.typsy.data.local.AppDatabase;
import com.example.typsy.data.local.entity.Coin;
import com.example.typsy.data.local.entity.Conversion;
import com.example.typsy.data.local.entity.Currency;
import com.example.typsy.data.local.entity.Price;

import java.util.List;
import java.util.Objects;

/**
 * Created by gravity on 11/7/17.
 */

public class ConversionDialogViewModel extends AndroidViewModel {

    // declare mutable fields
    private MutableLiveData<Resource<Price>> vmPrice = new MutableLiveData<>();
    private MutableLiveData<List<Coin>> vmCoinList = new MutableLiveData<>();
    private MutableLiveData<List<Currency>> vmCurrencyList = new MutableLiveData<>();
    private MutableLiveData<String> vmFCode = new MutableLiveData<>();
    private MutableLiveData<String> vmTCode = new MutableLiveData<>();
    private MutableLiveData<Coin> vmCoin = new MutableLiveData<>();
    private MutableLiveData<Currency> vmCurrency = new MutableLiveData<>();
    private MutableLiveData<String> vmSnackMessage = new MutableLiveData<>();
    private MutableLiveData<String> conversion_id = new MutableLiveData<>();
    private LiveData<Conversion> conversion = new MediatorLiveData<>();

    // data controller
    private DataRepository mRepository;

    public ConversionDialogViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        mRepository = DataRepository.getInstance(database);

        conversion = Transformations.switchMap(conversion_id, input -> mRepository.getConversion(input));
        vmCoinList = AppConstant.getCoinList();
        vmCurrencyList = AppConstant.getCurrencyList();

    }
    public void setConversion_id(String id) {
        if (Objects.equals(this.conversion_id.getValue(), id)) {
            return;
        }
        this.conversion_id.setValue(id);
    }

    public LiveData<Conversion> getConversion() {
        return conversion;
    }

    public void updateConversion(Conversion conversion) {
        mRepository.updateConversion(conversion);
    }

    public void setVmFCode(String fcode) {
        vmFCode.setValue(fcode);
    }

    public void setVmTCode(String tcode) {
        vmTCode.setValue(tcode);
    }

    public MutableLiveData<String> getVmFCode() {
        return vmFCode;
    }

    public MutableLiveData<String > getVmTCode() {
        return vmTCode;
    }

    public MutableLiveData<List<Coin>> getVmCoinList() {
        return vmCoinList;
    }

    public MutableLiveData<List<Currency>> getVmCurrencyList() {
        return vmCurrencyList;
    }


    public MutableLiveData<Coin> getVmCoin() {
        return vmCoin;
    }

    public void setVmCoin(Coin coin) {
        vmCoin.setValue(coin);
    }

    public MutableLiveData<Currency> getVmCurrency() {
        return vmCurrency;
    }

    public void setVmCurrency(Currency currency) {
        vmCurrency.setValue(currency);
    }

    public MutableLiveData<String> getVmSnackMessage() {
        return vmSnackMessage;
    }

    public void setVmSnackMessage(String message) {
        vmSnackMessage.setValue(message);
    }

    public void getResponse(String fcode, String tcode) {
        mRepository.getPriceResponse(fcode, tcode, resource -> vmPrice.setValue(resource));
    }

    public MutableLiveData<Resource<Price>> getVmPrice() {
        return vmPrice;
    }

}

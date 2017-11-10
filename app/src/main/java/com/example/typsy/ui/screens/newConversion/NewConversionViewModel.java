package com.example.typsy.ui.screens.newConversion;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
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

/**
 * Created by gravity on 11/7/17.
 */

public class NewConversionViewModel extends AndroidViewModel{

    private MutableLiveData<Resource<Price>> vmPrice = new MutableLiveData<>();
    private MutableLiveData<List<Coin>> vmCoinList = new MutableLiveData<>();
    private MutableLiveData<List<Currency>> vmCurrencyList = new MutableLiveData<>();
    private MutableLiveData<String> vmFCode = new MutableLiveData<>();
    private MutableLiveData<String> vmTCode = new MutableLiveData<>();
    private MutableLiveData<Coin> vmCoin = new MutableLiveData<>();
    private MutableLiveData<Currency> vmCurrency = new MutableLiveData<>();
    private MutableLiveData<String> vmSnackMessage = new MutableLiveData<>();

    private DataRepository mRepository;

    public NewConversionViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        mRepository = DataRepository.getInstance(database);
        getResponse(AppConstant.DEFAULT_COIN, AppConstant.DEFAULT_CURRENCY);
        vmCoinList = AppConstant.getCoinList();
        vmCurrencyList = AppConstant.getCurrencyList();
    }

    public MutableLiveData<List<Coin>> getVmCoinList() {
        return vmCoinList;
    }

    public MutableLiveData<List<Currency>> getVmCurrencyList() {
        return vmCurrencyList;
    }

    public void saveConversion(Conversion conversion) {
        mRepository.saveConversion(conversion);
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

    public MutableLiveData<Coin> getVmCoin() {
        return vmCoin;
    }

    public MutableLiveData<String> getVmSnackMessage() {
        return vmSnackMessage;
    }

    public void setVmSnackMessage(String message) {
        vmSnackMessage.setValue(message);
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

    public void getResponse(String fcode, String tcode) {
        mRepository.getPriceResponse(fcode, tcode, resource -> vmPrice.setValue(resource));
    }

    public MutableLiveData<Resource<Price>> getVmPrice() {
        return vmPrice;
    }
}

package com.example.typsy;

import android.arch.lifecycle.MutableLiveData;

import com.example.typsy.data.local.entity.Coin;
import com.example.typsy.data.local.entity.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gravity on 10/19/17.
 */


public class AppConstant {

    public static final String DEFAULT_COIN = "BTC";
    public static final String DEFAULT_CURRENCY = "NGN";
    public static final String BASE_URL_ONE = "https://min-api.cryptocompare.com";
    public static final String BASE_URL_TWO = "https://www.cryptocompare.com";
    public static final String BASE_URL_THREE = "https://restcountries.eu";

    public static MutableLiveData<List<Coin>> getCoinList() {
        final MutableLiveData<List<Coin>> liveData = new MutableLiveData<>();
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin("BTC", R.drawable.btc, "Bitcoin"));
        coins.add(new Coin("ETH", R.drawable.eth, "Ethereum"));
        coins.add(new Coin("LTC", R.drawable.ltc, "Litecoin"));
        coins.add(new Coin("DASH", R.drawable.dash, "DigitalCash"));
        coins.add(new Coin("BCH", R.drawable.bch, "Bitcoin Cash"));
        coins.add(new Coin("ZEC", R.drawable.zec, "ZCash"));
        coins.add(new Coin("NEO", R.drawable.neo, "NEO"));
        coins.add(new Coin("XMR", R.drawable.xmr, "Monero"));
        coins.add(new Coin("XRP", R.drawable.xrp, "Ripple"));
        coins.add(new Coin("EOS", R.drawable.eos, "EOS"));
        coins.add(new Coin("XEM", R.drawable.xem, "NEM"));
        coins.add(new Coin("XVG", R.drawable.xvg, "Verge"));
        coins.add(new Coin("CURE", R.drawable.cure, "Curecoin"));
        coins.add(new Coin("XVC", R.drawable.xvc, "Vcash"));
        coins.add(new Coin("VTC", R.drawable.vtc, "VertCoin"));
        coins.add(new Coin("VRC", R.drawable.vrc, "VeriCoin"));
        coins.add(new Coin("NXT", R.drawable.nxt, "NXT"));
        coins.add(new Coin("NAV", R.drawable.nav, "NavCoin"));
        coins.add(new Coin("ARK", R.drawable.ark, "ARK"));
        coins.add(new Coin("POT", R.drawable.pot, "PotCoin"));
        liveData.setValue(coins);

        return liveData;
    }

    public static MutableLiveData<List<Currency>> getCurrencyList() {
        final MutableLiveData<List<Currency>> liveData = new MutableLiveData<>();
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("NGN", R.drawable.ngn, "NG Naira"));
        currencies.add(new Currency("USD", R.drawable.usd, "US Dollar"));
        currencies.add(new Currency("GBP", R.drawable.gbp, "British Pound"));
        currencies.add(new Currency("EUR", R.drawable.eur, "Euro"));
        currencies.add(new Currency("JPY", R.drawable.jpy, "Japanese Yen"));
        currencies.add(new Currency("CAD", R.drawable.cad, "CA Dollar"));
        currencies.add(new Currency("AUD", R.drawable.aud, "Aus Dollar"));
        currencies.add(new Currency("INR", R.drawable.inr, "Indian Rupee"));
        currencies.add(new Currency("MYR", R.drawable.myr, "Ringgit"));
        currencies.add(new Currency("CNY", R.drawable.cny, "CH Yuan"));
        currencies.add(new Currency("NZD", R.drawable.nzd, "NZ Dollar"));
        currencies.add(new Currency("KRW", R.drawable.krw, "SK Won"));
        currencies.add(new Currency("CHF", R.drawable.chf, "Swiss Franc"));
        currencies.add(new Currency("MXN", R.drawable.mxn, "MX Peso"));
        currencies.add(new Currency("ZAR", R.drawable.zar, "SA Rand"));
        currencies.add(new Currency("IDR", R.drawable.idr, "Rupiah"));
        currencies.add(new Currency("EGP", R.drawable.egp, "Egyptian Pound"));
        currencies.add(new Currency("GHS", R.drawable.ghs, "Ghana Cedi"));
        currencies.add(new Currency("RUB", R.drawable.rub, "Russian Ruble"));
        currencies.add(new Currency("HKD", R.drawable.hkd, "HK Dollar"));

        liveData.setValue(currencies);
        return liveData;
    }
}
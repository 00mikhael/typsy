package com.example.typsy;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.typsy.data.local.entity.Coin;
import com.example.typsy.data.local.entity.Currency;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gravity on 10/18/17.
 */

public class Util {

    // formats double to two decimal places
    public static Double toTwoDecimal(Double value) {
        DecimalFormat formatter = new DecimalFormat(".##");
        formatter.setRoundingMode(RoundingMode.UP);
        return Double.valueOf(formatter.format(value));
    }

    // returns profit or loss percentage of conversion since created
    public static Double calculatePL(Double baseRate, Double newRate) {
        Double diff = newRate - baseRate;
        Double val = diff / baseRate;
        return toTwoDecimal(val * 100);
    }

    // returns current date from system calender
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        return formatter.format(calendar.getTime());
    }

    // returns the index of a selected string from the coinlist
    public static int getIndexCoin(List<Coin> coins, String selected){
        int index = 0;
        for (int i = 0; i < coins.size(); i++){
            if (coins.get(i).getCode().equals(selected)){
                index = i;
            }
        }
        return index;
    }

    // returns the index of a selected string from the currencylist
    public static int getIndexCurrency(List<Currency> currencies, String selected){
        int index = 0;
        for (int i = 0; i < currencies.size(); i++){
            if (currencies.get(i).getCode().equals(selected)){
                index = i;
            }
        }
        return index;
    }

    // returns a coin from a list of coins using the code
    public static Coin getCurrentCoin(List<Coin> coins, String code){
        Coin coin = new Coin();
        for (int i = 0; i < coins.size(); i++){
            if (coins.get(i).getCode().equals(code)){
                coin = coins.get(i);
            }
        }
        return coin;
    }

    // returns a currency from a list of currencies using the code
    public static Currency getCurrentCurrency(List<Currency> currencies, String code){
        Currency currency = new Currency();
        for (int i = 0; i < currencies.size(); i++){
            if (currencies.get(i).getCode().equals(code)){
                currency = currencies.get(i);
            }
        }
        return currency;
    }

    // adds a fragment to an activity
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.add(R.id.fragment_container, fragment);
        transaction.commitAllowingStateLoss();
    }

    // replaces a fragment in an activity and adds to back stack
    public static void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commitAllowingStateLoss();
    }

    // replaces a fragment in an activity
    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commitAllowingStateLoss();
    }
}

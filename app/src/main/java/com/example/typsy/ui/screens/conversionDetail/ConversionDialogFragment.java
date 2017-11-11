package com.example.typsy.ui.screens.conversionDetail;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.example.typsy.R;
import com.example.typsy.Util;
import com.example.typsy.data.Status;
import com.example.typsy.data.local.entity.Coin;
import com.example.typsy.data.local.entity.Conversion;
import com.example.typsy.data.local.entity.Currency;
import com.example.typsy.data.local.entity.Price;
import com.example.typsy.ui.CustomSpinnerAdapterCrypto;
import com.example.typsy.ui.CustomSpinnerAdapterCurrency;
import com.example.typsy.ui.screens.conversionList.ConversionListFragment;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gravity on 10/18/17.
 */

public class ConversionDialogFragment extends Fragment {

    private static final String CONVERSION_ID = "conversion_id";

    // private fields declaration
    private FragmentManager mFragmentManager;
    private AppCompatImageView mCryptoImage;
    private CircleImageView mCurrencyImage;
    private AppCompatTextView mCryptoName;
    private AppCompatTextView mCurrencyName;
    private AppCompatSpinner mCryptoSpinner;
    private AppCompatSpinner mCurrencySpinner;
    private AppCompatTextView mConversionValue;
    private AppCompatTextView mCurrencySymbol;
    private AppCompatTextView mCryptoSymbol;
    private AppCompatEditText mAmountToConvert;
    private AppCompatTextView mConnectionStatus;
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mDoneFab;
    private CustomSpinnerAdapterCrypto mCryptoSpinnerAdapter;
    private CustomSpinnerAdapterCurrency mCurrencySpinnerAdapter;

    private ConversionDialogViewModel mViewModel;
    private String fcode = null;
    private String tcode = null;
    private Double input = 0.0;
    private Double result = 0.0;
    private Conversion mConversion = new Conversion();
    private Coin mCoin = new Coin();
    private Currency mCurrency = new Currency();
    private Price mPrice = new Price();
    private List<Coin > mCoinList = new ArrayList<>();
    private List<Currency> mCurrencyList = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private String conversion_id;

    // returns current lifecycle
    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    // creates a new instance of the fragment with a conversion id
    public static ConversionDialogFragment newInstance(String conversion_id) {
        ConversionDialogFragment fragment = new ConversionDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CONVERSION_ID, conversion_id);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conversion_dialog, container, false);

        // creating an instance of ConversionDialogViewModel from ViewModelProviders
        mViewModel = ViewModelProviders.of(this).get(ConversionDialogViewModel.class);

        // gets conversion if from bundle
        conversion_id = getArguments().getString(CONVERSION_ID);

        // sets mutable conversion_id field in ViewModel to current conversion
        if (conversion_id != null) {
            mViewModel.setConversion_id(conversion_id);
        }

        // initializing fields
        mFragmentManager = getActivity().getSupportFragmentManager();
        mCryptoImage = view.findViewById(R.id.dialog_crypto_avatar);
        mCurrencyImage = view.findViewById(R.id.dialog_currency_avatar);
        mCryptoName =  view.findViewById(R.id.dialog_crypto_name);
        mCurrencyName = view.findViewById(R.id.dialog_currency_name);
        mCryptoSpinner = view.findViewById(R.id.dialog_crypto_spinner);
        mCurrencySpinner = view.findViewById(R.id.dialog_currency_spinner);
        mConversionValue = view.findViewById(R.id.dialog_conversion_value);
        mCurrencySymbol = view.findViewById(R.id.dialog_currency_symbol);
        mCryptoSymbol = view.findViewById(R.id.dialog_crypto_symbol);
        mAmountToConvert = view.findViewById(R.id.dialog_amount_to_convert);
        mConnectionStatus = view.findViewById(R.id.dialog_network_status);
        mConnectionStatus.setText(R.string.online);
        mCoordinatorLayout = view.findViewById(R.id.dialog_coordinator);
        mDoneFab = view.findViewById(R.id.dialog_fab);
        mCryptoSpinnerAdapter = new CustomSpinnerAdapterCrypto(getActivity());
        mCurrencySpinnerAdapter = new CustomSpinnerAdapterCurrency(getActivity());
        mCryptoSpinner.setAdapter(mCryptoSpinnerAdapter);
        mCurrencySpinner.setAdapter(mCurrencySpinnerAdapter);
        mViewModel.getConversion().observe(this, conversion -> {
            mConversion = conversion;
            mViewModel.setVmFCode(conversion.getFromCode());
            mViewModel.setVmTCode(conversion.getToCode());
            mConversionValue.setText(String.valueOf(conversion.getConversionResult()));
            mAmountToConvert.setText(String.valueOf(mConversion.getAmountToConvert()));
            mCryptoSpinner.setSelection(Util.getIndexCoin(mCoinList, mConversion.getFromCode()));
            mCurrencySpinner.setSelection(Util.getIndexCurrency(mCurrencyList, mConversion.getToCode()));
        });
        return view;
    }

    // onViewCreated - initialize observable fields
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // observe coin list
        mViewModel.getVmCoinList().observe(this, coins -> {
            mCoinList = coins;
            mCryptoSpinnerAdapter.setItems(coins);
            mViewModel.setVmCoin(Util.getCurrentCoin(mCoinList, mConversion.getFromCode()));
        });

        // observe currency list
        mViewModel.getVmCurrencyList().observe(this, currencies -> {
            mCurrencyList = currencies;
            mCurrencySpinnerAdapter.setItems(currencies);
            mViewModel.setVmCurrency(Util.getCurrentCurrency(mCurrencyList, mConversion.getToCode()));
        });

        // observe current coin
        mViewModel.getVmCoin().observe(this, coin -> {
            mCoin = coin;
            ConversionDialogFragment.this.onCoinChange(coin);
            mViewModel.setVmFCode(coin.getCode());
        });

        // observe current currency
        mViewModel.getVmCurrency().observe(this, currency -> {
            mCurrency = currency;
            onCurrencyChange(currency);
            mViewModel.setVmTCode(currency.getCode());
        });

        // observe current price and change states
        mViewModel.getVmPrice().observe(this, resource -> {
            if (resource.status.equals(Status.LOADING)) {
                if (resource.data != null) {
                    mPrice = resource.data;
                    onPriceChange(mPrice);
                    mViewModel.setVmSnackMessage(getString(R.string.loading));
                }
            }else if (resource.status.equals(Status.SUCCESS)) {
                mPrice = resource.data;
                onPriceChange(mPrice);
                mViewModel.setVmSnackMessage(getString(R.string.success));
            }else {
                //error
                mViewModel.setVmSnackMessage(getString(R.string.error));
            }

        });

        // observe coin spinner selected code and query price
        mViewModel.getVmFCode().observe(this, s -> {
            fcode = s;
            if (tcode != null) {
                mViewModel.getResponse(fcode, tcode);
            }
        });

        // observe currency spinner selected code and query price
        mViewModel.getVmTCode().observe(this, s -> {
            tcode = s;
            if (fcode != null) {
                mViewModel.getResponse(fcode, tcode);
            }
        });

        // observe and show snackbar messages
        mViewModel.getVmSnackMessage().observe(this, s -> {
            if (s != null) {
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, s, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
        setUpView();
    }

    // set up view listeners
    public void setUpView() {

        // click listener for crypto spinner
        mCryptoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mViewModel.setVmCoin(mCoinList.get(i));
                mConversionValue.setText(String.valueOf(Util.toTwoDecimal(0.0)));
                observeNetwork();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // click listener for currency spinner
        mCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mViewModel.setVmCurrency(mCurrencyList.get(i));
                mConversionValue.setText(String.valueOf(Util.toTwoDecimal(0.0)));
                observeNetwork();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // text change listener for amount to convert edittext
        mAmountToConvert.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!mAmountToConvert.getText().toString().equals("")) {
                    mViewModel.getResponse(fcode, tcode);
                    input = Double.valueOf(mAmountToConvert.getText().toString());
                    mConversionValue.setText(String.valueOf(Util.toTwoDecimal(calculateValue(mPrice))));
                }else {
                    mConversionValue.setText(String.valueOf(Util.toTwoDecimal(0.0)));
                }
                observeNetwork();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // click listener for done fab - updates conversion and saves
        mDoneFab.setOnClickListener(view1 -> {
            ConversionDialogFragment.this.hideSoftInputPanel(mAmountToConvert);
            mViewModel.updateConversion(ConversionDialogFragment.this.onConversionComplete(mCoin, mCurrency, mPrice));
            ConversionListFragment fragment = ConversionListFragment.newInstance();
            Util.replaceFragment(mFragmentManager, fragment);
        });
    }

    // set up view on price change
    private void onPriceChange(Price price) {
        mCryptoSymbol.setText(price.getfSym());
        mCurrencySymbol.setText(price.gettSym());
        mConversionValue.setText(String.valueOf(Util.toTwoDecimal(calculateValue(mPrice))));
    }

    // set up view on coin change
    private void onCoinChange(Coin coin) {
        Glide.with(this).load(coin.getImageUrl()).thumbnail(0.5f).into(mCryptoImage);
        mCryptoName.setText(coin.getCoinName());
    }

    // set up view on currency change
    private void onCurrencyChange(Currency currency) {
        Glide.with(this).load(currency.getFlag()).thumbnail(0.5f).into(mCurrencyImage);
        mCurrencyName.setText(currency.getName());
    }

    // calculate conversion value
    private Double calculateValue(Price price) {
        if (input != null) {
            result = price.getRawPrice() * input;
            return result;
        }
        return result;
    }

    // updates conversion when done fab is clicked
    private Conversion onConversionComplete(Coin coin, Currency currency, Price price) {
        mConversion.setAmountToConvert(input);
        mConversion.setConversionResult(result);
        mConversion.setFromCode(coin.getCode());
        mConversion.setToCode(currency.getCode());
        mConversion.setConversionRate(price.getRawPrice());
        mConversion.setFromSym(price.getfSym());
        mConversion.setToSym(price.gettSym());
        mConversion.setFromAvatarUrl(coin.getImageUrl());
        mConversion.setToAvatarUrl(currency.getFlag());
        mConversion.setCreatedDate(Util.getCurrentDate());
        mConversion.setCreatedText(getResources().getString(R.string.updated));
        mConversion.setPercentagePL(Util.calculatePL(mConversion.getBaseRate(), mConversion.getConversionRate()));
        return mConversion;
    }

    // hides keyboard
    private void hideSoftInputPanel(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    // observes network state and update view
    private void observeNetwork() {
        Single<Boolean> single = ReactiveNetwork.checkInternetConnectivity();
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnectedToTheInternet -> {
                    if (isConnectedToTheInternet) {
                        mConnectionStatus.setTextColor(ConversionDialogFragment.this.getResources().getColor(R.color.green));
                        mConnectionStatus.setText(R.string.online);
                    } else {
                        mConnectionStatus.setTextColor(ConversionDialogFragment.this.getResources().getColor(R.color.red));
                        mConnectionStatus.setText(R.string.offline);
                    }
                }));
    }

    // clear disposables
    @Override
    public void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }
}

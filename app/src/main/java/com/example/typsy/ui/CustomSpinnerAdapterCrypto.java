package com.example.typsy.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.typsy.R;
import com.example.typsy.data.local.entity.Coin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gravity on 11/4/17.
 */

public class CustomSpinnerAdapterCrypto extends BaseAdapter {

    private List<Coin> items;
    private LayoutInflater inflater;

    public CustomSpinnerAdapterCrypto(Context context) {
        this.items = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setItems(List<Coin> coins) {
        this.items = coins;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.spinner_item, null);

        AppCompatTextView textView = v.findViewById(R.id.spinner_item);
        textView.setText(items.get(i).getCode());
        return v;
    }
}

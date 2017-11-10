package com.example.typsy.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.typsy.R;
import com.example.typsy.Util;
import com.example.typsy.data.local.entity.Conversion;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gravity on 10/18/17.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ConversionViewHolder> {

    private Context mContext;
    private int mRowLayout;
    private List<Conversion> mConversions;
    private ClickListener listener;

    public BaseAdapter(Context context, int mRowLayout, ClickListener listener) {
        this.mContext = context;
        mConversions = new ArrayList<>();
        this.mRowLayout = mRowLayout;
        this.listener = listener;
    }

    public void add(Conversion conversion) {
        mConversions.add(conversion);
        notifyDataSetChanged();
    }

    public List<Conversion> getConversions() {
        return mConversions;
    }

    public void add(List<Conversion> conversions) {
        mConversions = conversions;
        notifyDataSetChanged();
    }

    public class ConversionViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView mCryptoCode, mCurrencyCode, mCrytoSymbol, mCurrencySymbol, mAmountToConvert, mConversionValue,
                                    mProfitLoss, mCreatedDate, mCreatedText;
        private AppCompatImageView mCryptoAvatar;
        private CircleImageView mCurrencyAvatar;
        public CardView container;
        public RelativeLayout viewBackground;

        public ConversionViewHolder(@NonNull View view) {
            super(view);
            mCryptoCode = view.findViewById(R.id.crypto_code);
            mCrytoSymbol = view.findViewById(R.id.crypto_symbol);
            mCurrencyCode = view.findViewById(R.id.currency_code);
            mCurrencySymbol = view.findViewById(R.id.currency_symbol);
            mAmountToConvert = view.findViewById(R.id.conversion_amount);
            mConversionValue = view.findViewById(R.id.conversion_value);
            mProfitLoss = view.findViewById(R.id.percentage_profit_loss);
            mCreatedDate = view.findViewById(R.id.updated_date);
            mCreatedText = view.findViewById(R.id.date_text);
            container = view.findViewById(R.id.card_container);
            mCryptoAvatar = view.findViewById(R.id.crypto_avatar);
            mCurrencyAvatar = view.findViewById(R.id.currency_avatar);
            viewBackground = view.findViewById(R.id.view_background);
        }

    }

    @NonNull
    @Override
    public BaseAdapter.ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new ConversionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseAdapter.ConversionViewHolder holder, final int position) {
        if (mConversions != null) {
            Glide.with(mContext).load(mConversions.get(position).getFromAvatarUrl()).thumbnail(0.5f).into(holder.mCryptoAvatar);
            Glide.with(mContext).load(mConversions.get(position).getToAvatarUrl()).thumbnail(0.5f).into(holder.mCurrencyAvatar);
            holder.mCrytoSymbol.setText(mConversions.get(position).getFromSym());
            holder.mCryptoCode.setText(mConversions.get(position).getFromCode());
            holder.mCurrencySymbol.setText(mConversions.get(position).getToSym());
            holder.mCurrencyCode.setText(mConversions.get(position).getToCode());
            holder.mAmountToConvert.setText(" " + String.valueOf(mConversions.get(position).getAmountToConvert()));
            holder.mConversionValue.setText(" " + String.valueOf(Util.toTwoDecimal(mConversions.get(position).getConversionResult())));
            holder.mCreatedDate.setText(String.valueOf(mConversions.get(position).getCreatedDate()));
            holder.mCreatedText.setText(mConversions.get(position).getCreatedText());
            String profit = String.valueOf(mConversions.get(position).getPercentagePL());
            if (profit.contains("-")) {
                String loss = profit.replace("-", "");
                holder.mProfitLoss.setTextColor(mContext.getResources().getColor(R.color.red));
                holder.mProfitLoss.setText(loss + "%");
            }else {
                holder.mProfitLoss.setTextColor(mContext.getResources().getColor(R.color.green));
                holder.mProfitLoss.setText(profit + "%");
            }
        }
        holder.container.setOnClickListener(view -> listener.onCardClicked(position));
    }

    @Override
    public int getItemCount() {
        return mConversions.size();
    }

    public void removeItem(int position) {
        mConversions.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Conversion conversion, int position) {
        mConversions.add(position, conversion);
        notifyItemInserted(position);
    }

    public interface ClickListener {
        void onCardClicked(int position);
    }
}


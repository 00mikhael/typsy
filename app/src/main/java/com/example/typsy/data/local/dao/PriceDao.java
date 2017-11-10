package com.example.typsy.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.typsy.data.local.entity.Price;

import io.reactivex.Single;

/**
 * Created by gravity on 11/3/17.
 */
@Dao
public interface PriceDao {
    @Query("SELECT * FROM price WHERE f_code LIKE :fcode AND "
            + "t_code LIKE :tcode")
    Single<Price> getPrice(String fcode, String tcode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPrice(Price price);
}

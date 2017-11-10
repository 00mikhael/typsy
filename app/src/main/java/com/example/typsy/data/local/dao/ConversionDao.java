package com.example.typsy.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.typsy.data.local.entity.Conversion;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by gravity on 10/18/17.
 */
@Dao
public interface ConversionDao {
    @Query("SELECT * FROM conversion")
    Flowable<List<Conversion>>  getConversionList();

    @Query("SELECT * FROM conversion WHERE id LIKE :id")
    Flowable<Conversion> getConversion(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertConversionList(List<Conversion> conversions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertConversion(Conversion conversion);

    @Update
    void updateConversion(Conversion conversion);

    @Delete
    void deleteConversion(Conversion conversion);

}

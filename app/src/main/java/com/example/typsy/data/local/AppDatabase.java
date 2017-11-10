package com.example.typsy.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.typsy.data.local.dao.ConversionDao;
import com.example.typsy.data.local.dao.PriceDao;
import com.example.typsy.data.local.entity.Conversion;
import com.example.typsy.data.local.entity.Price;

/**
 * Created by gravity on 10/20/17.
 */
@Database(entities = {Conversion.class, Price.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase sInstance;

    public abstract ConversionDao conversionDao();
    public abstract PriceDao priceDao();

    // Get a database instance
    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = create(context);
        }
        return sInstance;
    }

    // Create the database
    private static AppDatabase create(Context context) {
        RoomDatabase.Builder<AppDatabase> builder = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,
                "typsy-db");
        return builder
                .fallbackToDestructiveMigration()
                .build();
    }

}

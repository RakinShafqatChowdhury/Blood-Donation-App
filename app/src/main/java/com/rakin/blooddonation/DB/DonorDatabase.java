package com.rakin.blooddonation.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rakin.blooddonation.Daos.donorDao;
import com.rakin.blooddonation.Entities.donorInfo;

@Database(entities = {donorInfo.class}, version = 1)
public abstract class DonorDatabase extends RoomDatabase {
    private static DonorDatabase db;
    public abstract donorDao getDonorDao();

    public static DonorDatabase getInstance(Context context){
        if(db != null){
            return db;
        }

        db = Room.databaseBuilder(context,
                DonorDatabase.class,
                "donorDB")
                .allowMainThreadQueries()
                .build();
        return db;

    }

}

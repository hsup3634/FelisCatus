package com.example.feliscatus.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.feliscatus.Cat;

@Database(entities = {Cat.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CatDao catDao();

    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {
       /*if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "catDb")
                    .allowMainThreadQueries()
                    .build();
        }*/
        return instance;
    }
}

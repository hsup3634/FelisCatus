package com.example.feliscatus.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.feliscatus.Cat;

import java.util.List;

@Dao
public interface CatDao {

    @Query("SELECT * FROM cat ORDER BY breed ASC")
    List<Cat> getAll();

    @Query("SELECT * FROM cat WHERE id = :id")
    Cat findCatById(String id);

    @Query("SELECT * FROM cat WHERE breed = :breed")
    Cat findCatByBreed(String breed);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Cat> cats);


}

package com.dasktelor.simplehabittracker.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import com.dasktelor.simplehabittracker.data.Habit;

import java.util.List;

@Dao
public interface HabitDao {
    @Query("SELECT * FROM habit")
    LiveData<List<Habit>> getAll();
    @Query("SELECT * FROM habit WHERE id = :id")
    LiveData<Habit> getById(long id);
    @Insert
    void insert(Habit habit);

    @Update
    void update(Habit habit);
    @Delete
    void delete(Habit habit);
}

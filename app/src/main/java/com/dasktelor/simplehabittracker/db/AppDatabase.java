package com.dasktelor.simplehabittracker.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dasktelor.simplehabittracker.data.Habit;
import com.dasktelor.simplehabittracker.db.dao.HabitDao;

@Database(entities = {Habit.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HabitDao habitDao();
}

package com.dasktelor.simplehabittracker.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Habit {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long repeats;
    public String name;
}

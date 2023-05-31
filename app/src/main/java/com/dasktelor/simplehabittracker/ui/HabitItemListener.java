package com.dasktelor.simplehabittracker.ui;

import com.dasktelor.simplehabittracker.data.Habit;

public interface HabitItemListener {
    void onPlusClick(Habit habit);
    void onMinusClick(Habit habit);
    void onDeleteClick(Habit habit);
}

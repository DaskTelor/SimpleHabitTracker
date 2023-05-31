package com.dasktelor.simplehabittracker.ui;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.dasktelor.simplehabittracker.data.Habit;

import java.util.List;

public class HabitsDiffCallback extends DiffUtil.Callback {
    private final List<Habit> mOldHabitsList;
    private final List<Habit> mNewHabitsList;
    public HabitsDiffCallback(List<Habit> oldHabitsList, List<Habit> newHabitsList){
        mNewHabitsList = newHabitsList;
        mOldHabitsList = oldHabitsList;
    }
    @Override
    public int getOldListSize() {
        return mOldHabitsList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewHabitsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldHabitsList.get(oldItemPosition).id == mNewHabitsList.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Habit oldHabit = mOldHabitsList.get(oldItemPosition);
        final Habit newHabit = mNewHabitsList.get(newItemPosition);

        return oldHabit.name.equals(newHabit.name) && oldHabit.repeats == newHabit.repeats;
    }
}

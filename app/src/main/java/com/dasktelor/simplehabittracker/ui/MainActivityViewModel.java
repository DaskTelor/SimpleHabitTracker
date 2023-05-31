package com.dasktelor.simplehabittracker.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dasktelor.simplehabittracker.App;
import com.dasktelor.simplehabittracker.AppExecutors;
import com.dasktelor.simplehabittracker.data.Habit;
import com.dasktelor.simplehabittracker.db.AppDatabase;

import java.util.List;
import java.util.concurrent.Executor;

public class MainActivityViewModel extends AndroidViewModel {
    private final AppDatabase appDatabase;
    private final Executor dbExecutor;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        appDatabase = ((App) application).getDatabase();
        dbExecutor = AppExecutors.getInstance().diskIO();
    }

    public void addHabit(Habit habit) {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.habitDao().insert(habit);
            }
        });
    }

    public void deleteHabit(Habit habit) {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.habitDao().delete(habit);
            }
        });
    }

    public LiveData<List<Habit>> getHabits() {
        return appDatabase.habitDao().getAll();
    }

    public void updateHabit(Habit habit) {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.habitDao().update(habit);
            }
        });
    }
}

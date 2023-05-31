package com.dasktelor.simplehabittracker.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dasktelor.simplehabittracker.data.Habit;
import com.dasktelor.simplehabittracker.databinding.ActivityMainBinding;
import com.dasktelor.simplehabittracker.databinding.DialogAddBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        HabitsAdapter habitsAdapter = new HabitsAdapter(this, new HabitItemListener() {
            @Override
            public void onPlusClick(Habit habit) {

                Habit updateHabit = new Habit();

                updateHabit.repeats = habit.repeats + 1;
                updateHabit.name = habit.name;
                updateHabit.id = habit.id;

                mViewModel.updateHabit(updateHabit);
            }

            @Override
            public void onMinusClick(Habit habit) {
                if(habit.repeats == 0)
                    return;

                Habit updateHabit = new Habit();

                updateHabit.repeats = habit.repeats - 1;
                updateHabit.name = habit.name;
                updateHabit.id = habit.id;

                mViewModel.updateHabit(updateHabit);
            }

            @Override
            public void onDeleteClick(Habit habit) {
                mViewModel.deleteHabit(habit);
            }
        });

        mBinding.listHabits.setLayoutManager(new LinearLayoutManager(this));
        mBinding.listHabits.setAdapter(habitsAdapter);


        mViewModel.getHabits().observe(MainActivity.this, new Observer<List<Habit>>() {
            @Override
            public void onChanged(List<Habit> habits) {
                habitsAdapter.setData(habits);
            }
        });

        mBinding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddBinding dialogAddBinding = DialogAddBinding.inflate(getLayoutInflater());

                new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(true)
                        .setTitle("Add habit")
                        .setView(dialogAddBinding.getRoot())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Habit habit = new Habit();
                                habit.name = dialogAddBinding.nameHabit.getText().toString();
                                habit.repeats = 0;

                                mViewModel.addHabit(habit);
                            }
                        })
                        .create()
                        .show();
            }
        });
    }
}
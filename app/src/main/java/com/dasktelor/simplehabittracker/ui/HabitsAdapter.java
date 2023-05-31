package com.dasktelor.simplehabittracker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dasktelor.simplehabittracker.R;
import com.dasktelor.simplehabittracker.data.Habit;
import com.dasktelor.simplehabittracker.databinding.HabitItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HabitsAdapter extends RecyclerView.Adapter<HabitsAdapter.ViewHolder> {
    private List<Habit> mHabits;
    private LifecycleOwner mLifecycleOwner;
    private HabitItemListener mListener;
    public HabitsAdapter(LifecycleOwner lifecycleOwner, HabitItemListener listener){
        mHabits = new ArrayList<>();
        mLifecycleOwner = lifecycleOwner;
        mListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HabitItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.habit_item, parent, false);

        ViewHolder holder = new ViewHolder(binding);

        Button buttonPlus = holder.binding.buttonPlus;
        Button buttonMinus = holder.binding.buttonMinus;
        Button buttonDelete = holder.binding.buttonDelete;

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onPlusClick(mHabits.get(holder.getAdapterPosition()));
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMinusClick(mHabits.get(holder.getAdapterPosition()));
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDeleteClick(mHabits.get(holder.getAdapterPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mHabits.get(position));
    }

    @Override
    public int getItemCount() {
        return mHabits.size();
    }

    public void setData(List<Habit> habits){
        final HabitsDiffCallback diffCallback = new HabitsDiffCallback(mHabits, habits);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        mHabits.clear();
        mHabits.addAll(habits);
        diffResult.dispatchUpdatesTo(this);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final HabitItemBinding binding;
        public ViewHolder(@NonNull HabitItemBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Habit habit){
            binding.setLifecycleOwner(mLifecycleOwner);
            binding.setHabit(habit);
        }
    }
}

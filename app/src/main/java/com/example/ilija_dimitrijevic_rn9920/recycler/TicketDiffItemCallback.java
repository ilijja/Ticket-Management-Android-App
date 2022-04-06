package com.example.ilija_dimitrijevic_rn9920.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.ilija_dimitrijevic_rn9920.model.Ticket;

public class TicketDiffItemCallback extends DiffUtil.ItemCallback<Ticket> {
    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getDescription().equals(newItem.getDescription()) && oldItem.getPriorityType().toString().equals(newItem.getPriorityType().toString())
        && oldItem.getTicketType().toString().equals(newItem.getTicketType().toString()) && oldItem.getEstimation()==newItem.getEstimation() &&
                oldItem.getTittle().equals(newItem.getTittle());
    }

}

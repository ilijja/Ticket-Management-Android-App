package com.example.ilija_dimitrijevic_rn9920.recycler.adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilija_dimitrijevic_rn9920.R;
import com.example.ilija_dimitrijevic_rn9920.model.Ticket;
import com.example.ilija_dimitrijevic_rn9920.model.TicketType;

import java.util.function.Consumer;

public class TicketAdapterDone extends ListAdapter<Ticket,TicketAdapterDone.ViewHolder> {


    private final Consumer<Pair<Ticket,String>> onTicketClicked;

    public TicketAdapterDone(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Pair<Ticket, String>> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_done_item,parent,false);
        return new ViewHolder(view, data -> {
            Ticket ticket = getItem(data.second);
            onTicketClicked.accept(new Pair<Ticket,String>(ticket,data.first));
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = this.getItem(position);
        holder.bind(ticket);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ticketImage;
        TextView ticketName;
        TextView ticketDescription;

        public ViewHolder(@NonNull View itemView, Consumer<Pair<String,Integer>> onItemClicked) {
            super(itemView);
            this.init();
            itemView.setOnClickListener(view -> {
                onItemClicked.accept(new Pair<>("item",this.getBindingAdapterPosition()));
            });
        }

        public void bind(Ticket ticket){

            if(ticket.getTicketType() == TicketType.BUG){
                ticketImage.setImageResource(R.drawable.bug_icon);
            }else if(ticket.getTicketType() == TicketType.ENHANCEMENT){
                ticketImage.setImageResource(R.drawable.arrow_icon);
            }

            ticketName.setText(ticket.getTittle());
            ticketDescription.setText(ticket.getDescription());

        }

        private void init(){
            ticketImage = itemView.findViewById(R.id.doneTicketImage);
            ticketName = itemView.findViewById(R.id.doneTicketName);
            ticketDescription = itemView.findViewById(R.id.doneTicketDescription);
        }

    }

}

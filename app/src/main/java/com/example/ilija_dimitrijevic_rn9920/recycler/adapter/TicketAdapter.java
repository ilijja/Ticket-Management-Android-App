package com.example.ilija_dimitrijevic_rn9920.recycler.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilija_dimitrijevic_rn9920.R;
import com.example.ilija_dimitrijevic_rn9920.activities.MainActivity;
import com.example.ilija_dimitrijevic_rn9920.model.Ticket;
import com.example.ilija_dimitrijevic_rn9920.model.TicketType;

import java.util.function.Consumer;

public class TicketAdapter extends ListAdapter<Ticket,TicketAdapter.ViewHolder> {

    private final Consumer<Pair<Ticket,String>> onTicketClicked;

    public TicketAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Pair<Ticket,String>> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item,parent,false);
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
        Button btnRightArrow;
        Button btnDeleteTicket;
        CardView cardView;

        public ViewHolder(@NonNull View itemView, Consumer<Pair<String,Integer>> onItemClicked) {
            super(itemView);
            this.init();
            btnDeleteTicket.setOnClickListener(view -> {
                    onItemClicked.accept(new Pair<>("remove",getBindingAdapterPosition()));
            });
            btnRightArrow.setOnClickListener(view -> {
                onItemClicked.accept(new Pair<>("move",getBindingAdapterPosition()));
            });
            itemView.setOnClickListener(view -> {
                onItemClicked.accept(new Pair<>("item",getBindingAdapterPosition()));
            });
        }

        private void init(){
            ticketImage = itemView.findViewById(R.id.ticketImage);
            ticketName = itemView.findViewById(R.id.ticketName);
            ticketDescription = itemView.findViewById(R.id.ticketDescription);
            btnRightArrow = itemView.findViewById(R.id.btnRightArrow);
            btnDeleteTicket = itemView.findViewById(R.id.btnDeleteTicket);
            cardView = itemView.findViewById(R.id.cardView4);
        }

        SharedPreferences sharedPreferences;

        public void bind(Ticket ticket){

            if(ticket.getTicketType() == TicketType.BUG){
                ticketImage.setImageResource(R.drawable.bug_icon);
            }else if(ticket.getTicketType() == TicketType.ENHANCEMENT){
                ticketImage.setImageResource(R.drawable.arrow_icon);
            }

            ticketName.setText(ticket.getTittle());
            ticketDescription.setText(ticket.getDescription());

            sharedPreferences = itemView.getContext().getSharedPreferences(itemView.getContext().getPackageName(), Context.MODE_PRIVATE);
            if(sharedPreferences.contains(MainActivity.USERNAME_KEY)
                    && !sharedPreferences.getString(MainActivity.USERNAME_KEY,null).
                    equals("admin")){
                    btnDeleteTicket.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
            }




        }

    }
}

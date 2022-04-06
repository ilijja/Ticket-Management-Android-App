package com.example.ilija_dimitrijevic_rn9920.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ilija_dimitrijevic_rn9920.activities.AppActivity;
import com.example.ilija_dimitrijevic_rn9920.activities.MainActivity;
import com.example.ilija_dimitrijevic_rn9920.model.Ticket;
import com.example.ilija_dimitrijevic_rn9920.model.TicketType;
import com.example.ilija_dimitrijevic_rn9920.R;

public class SingleTicketFragment extends Fragment {

    private ImageView ticketImage;
    private Button button;
    private TextView tvType;
    private TextView tvPriority;
    private TextView tvEstimation;
    private TextView tvLoggedTime;
    private TextView tvTicketDescription;
    private TextView tvTittle;
    private boolean allowEdit;

    private Ticket ticket;


    public SingleTicketFragment(Ticket ticket,boolean allowEdit) {
        super(R.layout.fragment_ticket_single);
        this.ticket = ticket;
        this.allowEdit = allowEdit;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.init(view);
    }

    private void init(View view){
        initFields(view);
        initListeners();
        initValues();
    }

    private void initListeners(){
        tvLoggedTime.setOnClickListener(view -> {
            ticket.setLoggedTime(ticket.getLoggedTime() + 1);
            tvLoggedTime.setText(String.valueOf(ticket.getLoggedTime()));
        });
        tvLoggedTime.setOnLongClickListener(view -> {
            if(ticket.getLoggedTime()>0){
                ticket.setLoggedTime(ticket.getLoggedTime() - 1);
                tvLoggedTime.setText(String.valueOf(ticket.getLoggedTime()));
            }
            return true;
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(MainActivity.USERNAME_KEY,null);
        if(username!=null && !username.equals("admin") || allowEdit==false){
            button.setVisibility(View.GONE);
        }
        button.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = createTransactionWithAnimation();
            fragmentTransaction.replace(R.id.activityApp,new EditTicketFragment(ticket));
            fragmentTransaction.commit();

        });
    }

    private void initFields(View view){
        ticketImage = view.findViewById(R.id.sfImageView);
        button = view.findViewById(R.id.sfEditBtn);
        tvType = view.findViewById(R.id.sfTypeValue);
        tvEstimation = view.findViewById(R.id.sfEstimationValue);
        tvLoggedTime = view.findViewById(R.id.sfLoggedTimeValue);
        tvPriority = view.findViewById(R.id.sfPriorityValue);
        tvTicketDescription = view.findViewById(R.id.sfTicketDescValue);
        tvTittle = view.findViewById(R.id.sfTittle);
    }

    private void initValues(){
        if(ticket.getTicketType() == TicketType.BUG){
            ticketImage.setImageResource(R.drawable.bug_icon);
        }else
            ticketImage.setImageResource(R.drawable.arrow_icon);

        tvType.setText(ticket.getTicketType().toString());
        tvEstimation.setText(String.valueOf(ticket.getEstimation()));
        tvTicketDescription.setText(ticket.getDescription());
        tvPriority.setText(ticket.getPriorityType().toString());
        tvLoggedTime.setText(String.valueOf(ticket.getLoggedTime()));
        tvTittle.setText(ticket.getTittle());
    }

    private FragmentTransaction createTransactionWithAnimation() {
        FragmentTransaction transaction = ((AppActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        return transaction;
    }

}

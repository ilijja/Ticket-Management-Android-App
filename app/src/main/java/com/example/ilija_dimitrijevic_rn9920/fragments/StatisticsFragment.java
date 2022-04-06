package com.example.ilija_dimitrijevic_rn9920.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ilija_dimitrijevic_rn9920.model.Ticket;
import com.example.ilija_dimitrijevic_rn9920.model.TicketType;
import com.example.ilija_dimitrijevic_rn9920.viewmodels.RecyclerViewModel;
import com.example.ilija_dimitrijevic_rn9920.R;

import java.util.List;

public class StatisticsFragment extends Fragment {

    RecyclerViewModel recyclerViewModel;
    TextView todoNumber;
    TextView todoEnhaincments;
    TextView todoBuggs;
    TextView inProgressNumber;
    TextView inProgressEnhaincments;
    TextView inProgressBuggs;
    TextView doneNumber;
    TextView doneEnhaincments;
    TextView doneBuggs;


    public StatisticsFragment() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        init(view);
    }

    private void init(View view){
        initFields(view);
        initObservers();
    }

    private void initObservers(){
        recyclerViewModel.getDoneStatistics().observe(this.getViewLifecycleOwner(),tickets -> {
            int bugs = findBugs(tickets);
            int enhaincments = findEnhaincments(tickets);
            doneBuggs.setText(String.valueOf(bugs));
            doneEnhaincments.setText(String.valueOf(enhaincments));
            doneNumber.setText(String.valueOf(bugs+enhaincments));
        });

        recyclerViewModel.getInProgressStatistics().observe(this.getViewLifecycleOwner(),tickets -> {
            int bugs = findBugs(tickets);
            int enhaincments = findEnhaincments(tickets);
            inProgressBuggs.setText(String.valueOf(bugs));
            inProgressEnhaincments.setText(String.valueOf(enhaincments));
            inProgressNumber.setText(String.valueOf(bugs+enhaincments));
        });

        recyclerViewModel.getTodoStatistics().observe(this.getViewLifecycleOwner(),tickets -> {
            int bugs = findBugs(tickets);
            int enhaincments = findEnhaincments(tickets);
            todoBuggs.setText(String.valueOf(bugs));
            todoEnhaincments.setText(String.valueOf(enhaincments));
            todoNumber.setText(String.valueOf(bugs+enhaincments));
        });
    }

    private int findBugs(List<Ticket> tickets){
        int cnt = 0;
        for (Ticket ticket:tickets){
            if(ticket.getTicketType() == TicketType.BUG)
                cnt+=1;
        }
        return cnt;
    }

    private int findEnhaincments(List<Ticket> tickets){
        int cnt = 0;
        for (Ticket ticket:tickets){
            if(ticket.getTicketType() == TicketType.ENHANCEMENT)
                cnt+=1;
        }
        return cnt;
    }

    private void initFields(View view){
        todoNumber = view.findViewById(R.id.todoNumber);
        todoBuggs = view.findViewById(R.id.todoBugsNumber);
        todoEnhaincments = view.findViewById(R.id.todoEnhacementsNumber);

        inProgressNumber = view.findViewById(R.id.inProgressNumber);
        inProgressBuggs = view.findViewById(R.id.ipBugsNumber);
        inProgressEnhaincments = view.findViewById(R.id.ipEnhacementsNumber);

        doneNumber = view.findViewById(R.id.doneNumber);
        doneBuggs = view.findViewById(R.id.doneBugsNumber);
        doneEnhaincments = view.findViewById(R.id.doneEnhacementsNumber);
    }



}

package com.example.ilija_dimitrijevic_rn9920.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilija_dimitrijevic_rn9920.activities.AppActivity;
import com.example.ilija_dimitrijevic_rn9920.model.Ticket;
import com.example.ilija_dimitrijevic_rn9920.recycler.TicketDiffItemCallback;
import com.example.ilija_dimitrijevic_rn9920.recycler.adapter.TicketAdapter;
import com.example.ilija_dimitrijevic_rn9920.viewmodels.RecyclerViewModel;
import com.example.ilija_dimitrijevic_rn9920.R;

public class ToDoFragment extends Fragment {

    private EditText searchInput;
    private RecyclerView recyclerView;


    private TicketAdapter ticketAdapter;
    private RecyclerViewModel recyclerViewModel;


    public ToDoFragment(){
        super(R.layout.fragment_todo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        init(view);
    }

    private void init(View view){
        initView(view);
        initListenners();
        initObservers();
        initRecycler();
    }

    private void initListenners(){
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                recyclerViewModel.filterTodoTickets(text.toString());
            }
        });
    }


    private void initView(View view){
        recyclerView = view.findViewById(R.id.recyclerViewTodo);
        searchInput = view.findViewById(R.id.searchInput);
    }
    private void initObservers(){
        recyclerViewModel.getToDoTickets().observe(this.getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);
        });
    }

    private void initRecycler(){
        ticketAdapter = new TicketAdapter(new TicketDiffItemCallback(),data -> {
            if(data.second.equals("remove")){
                Ticket ticket = data.first;
                recyclerViewModel.removeTicket(ticket.getId());
            }else if(data.second.equals("move")){
                Ticket ticket = data.first;
                recyclerViewModel.sendToInProgressMutableLiveData(ticket.getId());
            }else if(data.second.equals("item")){
                FragmentTransaction fragmentTransaction = createTransactionWithAnimation();
                fragmentTransaction.replace(R.id.activityApp,new SingleTicketFragment(data.first,true));
                fragmentTransaction.commit();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(ticketAdapter);
    }

    private FragmentTransaction createTransactionWithAnimation() {
        FragmentTransaction transaction = ((AppActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        return transaction;
    }


}

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
import com.example.ilija_dimitrijevic_rn9920.recycler.TicketDiffItemCallback;
import com.example.ilija_dimitrijevic_rn9920.recycler.adapter.TicketAdapterInProgress;
import com.example.ilija_dimitrijevic_rn9920.viewmodels.RecyclerViewModel;
import com.example.ilija_dimitrijevic_rn9920.R;

public class InProgressFragment extends Fragment {

    private EditText searchInput;
    private RecyclerView recyclerView;


    private TicketAdapterInProgress ticketAdapter;
    private RecyclerViewModel recyclerViewModel;


    public InProgressFragment() {
        super(R.layout.fragment_in_progress);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        init(view);
    }

    private void init(View view){
        initView(view);
        initObservers();
        initListeners();
        initRecycler();
    }

    private void initListeners(){
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                recyclerViewModel.filterInProgressTickets(text.toString());
            }
        });
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recyclerViewInProgress);
        searchInput = view.findViewById(R.id.inProgressSearchInput);
    }
    private void initObservers(){
        recyclerViewModel.getInProgressTickets().observe(this.getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);
        });
    }

    private void initRecycler(){
        ticketAdapter = new TicketAdapterInProgress(new TicketDiffItemCallback(), data -> {
            if(data.second.equals("backward")){
                recyclerViewModel.sendToTodoMutableLiveData(data.first.getId());
            }else if(data.second.equals("forward")){
                recyclerViewModel.sendToDoneMutableLiveData(data.first.getId());
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

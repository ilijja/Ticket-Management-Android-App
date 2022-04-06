package com.example.ilija_dimitrijevic_rn9920.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ilija_dimitrijevic_rn9920.model.PriorityType;
import com.example.ilija_dimitrijevic_rn9920.model.Ticket;
import com.example.ilija_dimitrijevic_rn9920.model.TicketType;
import com.example.ilija_dimitrijevic_rn9920.viewmodels.RecyclerViewModel;
import com.example.ilija_dimitrijevic_rn9920.R;

import java.util.Arrays;
import java.util.List;

public class EditTicketFragment extends Fragment {

    private Spinner typeSpinner;
    RecyclerViewModel recyclerViewModel;
    Ticket ticket;
    private Spinner prioritySpinner;
    private String spinnerTypeValue;
    private String spinnerPriorityValue;
    private EditText textFieldEst;
    private EditText textFieldTicketTittle;
    private EditText textFieldTextDescription;
    String[] ticketType = {"Ticket Type", "Bug", "Enhancement"};
    String[] ticketPriority = {"Priority", "Highest", "High", "Medium", "Low", "Lowest"};
    private Button saveTicket;
    List<String> arrayTicketType;
    List<String> arrayPriorityType;

    public EditTicketFragment(Ticket ticket) {
        super(R.layout.fragment_ticket_edit);
        this.ticket = ticket;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }


    private void init(View view) {
        this.initTypeSpinner(view);
        this.initprioritySpinner(view);
        this.initFields(view);
        this.initListeners();
    }

    private void initListeners(){
        saveTicket.setOnClickListener(view -> {
            if(this.validate()){
                this.setTicket();
                recyclerViewModel.setEditedTicket(ticket);
            }else
                Toast.makeText(this.getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean validate(){
        spinnerTypeValue = ((TextView)typeSpinner.getSelectedView()).getText().toString();
        spinnerPriorityValue = ((TextView)prioritySpinner.getSelectedView()).getText().toString();

        if(this.spinnerTypeValue.equals("Ticket Type") || this.spinnerPriorityValue.equals("Priority")
                || this.textFieldTextDescription.getText().toString().equals("") || this.textFieldEst.getText().toString().equals("") || this.textFieldTicketTittle.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    private void initFields(View view){

        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);

        arrayTicketType = Arrays.asList(this.ticketType);
        arrayPriorityType = Arrays.asList(this.ticketPriority);
        this.saveTicket = view.findViewById(R.id.teSaveTicket);
        this.textFieldEst = view.findViewById(R.id.teEstEditText);
        this.textFieldTicketTittle = view.findViewById(R.id.teTicketTittleEditText);
        this.textFieldTextDescription = view.findViewById(R.id.teTicketDescriptionEditText);

        textFieldEst.setText(String.valueOf(ticket.getEstimation()));
        textFieldTicketTittle.setText(ticket.getTittle());
        textFieldTextDescription.setText(ticket.getDescription());
    }

    private void initTypeSpinner(View view){
        typeSpinner = view.findViewById(R.id.teTypeSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, this.ticketType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String type = ticket.getTicketType().toString().substring(0,1).toUpperCase() + ticket.getTicketType().toString().substring(1).toLowerCase();
        int pos = adapter.getPosition(type);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setSelection(pos);
    }

    private void initprioritySpinner(View view) {
        prioritySpinner = view.findViewById(R.id.tePrioritySpinner);
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, this.ticketPriority);
        String type = ticket.getPriorityType().toString().substring(0,1).toUpperCase() + ticket.getPriorityType().toString().substring(1).toLowerCase();
        int pos = adapter.getPosition(type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapter);
        prioritySpinner.setSelection(pos);

    }

    private void setTicket(){
        ticket.setTicketType(TicketType.valueOf(spinnerTypeValue.toUpperCase()));
        ticket.setPriorityType(PriorityType.valueOf(spinnerPriorityValue.toUpperCase()));
        ticket.setTittle(textFieldTicketTittle.getText().toString());
        ticket.setEstimation(Integer.valueOf(textFieldEst.getText().toString()));
        ticket.setDescription(textFieldTextDescription.getText().toString());
    }



}

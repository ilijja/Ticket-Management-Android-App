package com.example.ilija_dimitrijevic_rn9920.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ilija_dimitrijevic_rn9920.model.PriorityType;
import com.example.ilija_dimitrijevic_rn9920.model.TicketType;
import com.example.ilija_dimitrijevic_rn9920.viewmodels.RecyclerViewModel;
import com.example.ilija_dimitrijevic_rn9920.R;

import java.util.Arrays;
import java.util.List;

public class AddTicketFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner typeSpinner;
    private Spinner prioritySpinner;
    private String spinnerTypeValue;
    private String spinnerPriorityValue;
    private EditText textFieldEst;
    private EditText textFieldTicketTittle;
    private EditText textFieldTextDescription;
    String[] ticketType = {"Ticket Type", "Bug", "Enhancement"};
    String[] ticketPriority = {"Priority", "Highest", "High", "Medium", "Low", "Lowest"};
    private Button btnAddTicket;
    List<String> arrayTicketType;
    List<String> arrayPriorityType;
    RecyclerViewModel recyclerViewModel;

    public AddTicketFragment() {
        super(R.layout.fragment_ticket_add);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        this.init(view);
    }

    private void init(View view) {
        this.initTypeSpinner(view);
        this.initprioritySpinner(view);
        this.initFields(view);
        this.initListeners();
    }

    private void initListeners(){
        this.btnAddTicket.setOnClickListener(data -> {
            boolean val = this.validate();
            if(val){
                recyclerViewModel.addTicket(TicketType.valueOf(spinnerTypeValue.toUpperCase()), PriorityType.valueOf(spinnerPriorityValue.toUpperCase()),Integer.valueOf(textFieldEst.getText().toString()),textFieldTicketTittle.getText().toString(),textFieldTextDescription.getText().toString());
                Toast.makeText(this.getContext(), "Ticked added succesfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this.getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate(){
        if(this.spinnerTypeValue.equals("Ticket Type") || this.spinnerPriorityValue.equals("Priority")
        || this.textFieldTextDescription.getText().toString().equals("") || this.textFieldEst.getText().toString().equals("") || this.textFieldTicketTittle.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    private void initFields(View view){
        arrayTicketType = Arrays.asList(this.ticketType);
        arrayPriorityType = Arrays.asList(this.ticketPriority);
        this.btnAddTicket = view.findViewById(R.id.addTicketBtn);
        this.textFieldEst = view.findViewById(R.id.estEditText);
        this.textFieldTicketTittle = view.findViewById(R.id.ticketTittleEditText);
        this.textFieldTextDescription = view.findViewById(R.id.ticketDescriptionEditText);
    }

    private void initTypeSpinner(View view){
        typeSpinner = view.findViewById(R.id.typeSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, this.ticketType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(this);
    }

    private void initprioritySpinner(View view) {
        prioritySpinner = view.findViewById(R.id.prioritySpinner);
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, this.ticketPriority);
        prioritySpinner.setAdapter(adapter);
        prioritySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String value = adapterView.getItemAtPosition(i).toString();
        if(this.arrayTicketType.contains(value)){
            this.spinnerTypeValue = value;
        }else if(this.arrayPriorityType.contains(value)){
            this.spinnerPriorityValue = value;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

}

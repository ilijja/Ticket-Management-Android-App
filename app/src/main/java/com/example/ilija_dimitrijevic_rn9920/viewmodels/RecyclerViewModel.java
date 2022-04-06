package com.example.ilija_dimitrijevic_rn9920.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ilija_dimitrijevic_rn9920.model.PriorityType;
import com.example.ilija_dimitrijevic_rn9920.model.Ticket;
import com.example.ilija_dimitrijevic_rn9920.model.TicketType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerViewModel extends ViewModel {

    public static int counter = 5;
    private final MutableLiveData<List<Ticket>> todoTicketsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgressTicketsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> doneTicketsLiveData = new MutableLiveData<>();

    private final MutableLiveData<List<Ticket>> todoStatistics = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgressStatistics = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> doneStatistics = new MutableLiveData<>();

    private ArrayList<Ticket> todoTicketList = new ArrayList<>();
    private ArrayList<Ticket> inProgressTicketList = new ArrayList<>();
    private ArrayList<Ticket> doneTicketList = new ArrayList<>();

    public RecyclerViewModel() {
        for(int i=0;i<5;i++){
            Ticket ticket = new Ticket(i, TicketType.BUG, PriorityType.MEDIUM,10,"Bug" + i,"Ticket description");
            todoTicketList.add(ticket);
        }

        ArrayList<Ticket> ticketsToAdd = new ArrayList<>(todoTicketList);
        this.todoTicketsLiveData.setValue(ticketsToAdd);
    }

    public LiveData<List<Ticket>> getToDoTickets() {
        return todoTicketsLiveData;
    }

    public LiveData<List<Ticket>> getInProgressTickets() {
        return inProgressTicketsLiveData;
    }

    public LiveData<List<Ticket>> getDoneTicketsLiveData() {
        return doneTicketsLiveData;
    }

    public LiveData<List<Ticket>> getTodoStatistics() {
        return todoStatistics;
    }

    public LiveData<List<Ticket>> getInProgressStatistics() {
        return inProgressStatistics;
    }

    public LiveData<List<Ticket>> getDoneStatistics() {
        return doneStatistics;
    }

    public void sendToDoneMutableLiveData(int id){
        Ticket ticket = null;
        for(Ticket t:inProgressTicketList){
            if(id==t.getId()){
                ticket = t;
            }
        }
        if(ticket!=null) {
            doneTicketList.add(ticket);
            inProgressTicketList.remove(ticket);
            ArrayList<Ticket> toAdd = new ArrayList<>(doneTicketList);
            ArrayList<Ticket> toRemove = new ArrayList<>(inProgressTicketList);
            this.sortTickets(toAdd);
            this.sortTickets(toRemove);

            doneTicketsLiveData.setValue(toAdd);
            doneStatistics.setValue(toAdd);

            inProgressTicketsLiveData.setValue(toRemove);
            inProgressStatistics.setValue(toRemove);

        }
    }


    public void setEditedTicket(Ticket ticket){
        notifyObservers(ticket);
    }

    public void sendToInProgressMutableLiveData(int id){
        Ticket ticket = null;
        for(Ticket t:todoTicketList){
            if(id==t.getId()){
                ticket = t;
            }
        }
        if(ticket!=null) {
            inProgressTicketList.add(ticket);
            todoTicketList.remove(ticket);
            ArrayList<Ticket> toAdd = new ArrayList<>(inProgressTicketList);
            ArrayList<Ticket> toRemove = new ArrayList<>(todoTicketList);
            this.sortTickets(toAdd);
            this.sortTickets(toRemove);
            inProgressTicketsLiveData.setValue(toAdd);
            inProgressStatistics.setValue(toAdd);

            todoTicketsLiveData.setValue(toRemove);
            todoStatistics.setValue(toRemove);
        }
    }

    public void sendToTodoMutableLiveData(int id){
        Ticket ticket = null;
        for(Ticket t:inProgressTicketList){
            if(id==t.getId()){
                ticket = t;
            }
        }
        if(ticket!=null) {
            inProgressTicketList.remove(ticket);
            todoTicketList.add(ticket);
            ArrayList<Ticket> toRemove = new ArrayList<>(inProgressTicketList);
            ArrayList<Ticket> toAdd = new ArrayList<>(todoTicketList);
            this.sortTickets(toAdd);
            this.sortTickets(toRemove);
            inProgressTicketsLiveData.setValue(toRemove);
            inProgressStatistics.setValue(toRemove);

            todoStatistics.setValue(toAdd);
            todoTicketsLiveData.setValue(toAdd);
        }
    }

    public void filterTodoTickets(String filter){
        ArrayList<Ticket> toAdd = this.filterTickets(todoTicketList,filter);
        todoTicketsLiveData.setValue(toAdd);
    }

    public void filterInProgressTickets(String filter){
        ArrayList<Ticket> toAdd = this.filterTickets(inProgressTicketList,filter);
        inProgressTicketsLiveData.setValue(toAdd);
    }

    public void filterDoneTickets(String filter){
        ArrayList<Ticket> toAdd = this.filterTickets(doneTicketList,filter);
        doneTicketsLiveData.setValue(toAdd);
    }

    public void addTicket(TicketType ticketType,PriorityType priorityType,int estimation,String tittle,String description){
        Ticket ticket = new Ticket(counter++,ticketType,priorityType,estimation,tittle,description);
        todoTicketList.add(ticket);
        ArrayList<Ticket> toAdd = new ArrayList<>(todoTicketList);
        sortTickets(toAdd);
        todoTicketsLiveData.setValue(toAdd);
        todoStatistics.setValue(toAdd);
    }

    public void removeTicket(int id){
        Ticket ticketRm = null;
        for(Ticket ticket:todoTicketList){
            if(id==ticket.getId()){
                ticketRm = ticket;
            }
        }
        if(ticketRm!=null) {
            todoTicketList.remove(ticketRm);
            ArrayList<Ticket> toAdd = new ArrayList<>(todoTicketList);
            sortTickets(toAdd);
            todoTicketsLiveData.setValue(toAdd);
            todoStatistics.setValue(toAdd);
        }
    }

    private void sortTickets(ArrayList<Ticket> tickets){
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket ticket, Ticket t1) {
                return ticket.getTittle().compareTo(t1.getTittle());
            }
        });
    }

    private ArrayList<Ticket> filterTickets(ArrayList<Ticket> tickets,String filter){
        ArrayList<Ticket> toAdd = new ArrayList<>();
        tickets.forEach( ticket -> {
            if(ticket.getTittle().toLowerCase().startsWith(filter.toLowerCase())){
                toAdd.add(ticket);
            }
        });
        return toAdd;
    }

    private void notifyObservers(Ticket ticket){


        for(Ticket t:this.todoTicketList){
            if(ticket.getId() == t.getId()){
                todoTicketList.remove(t);
                Ticket ticketToAdd = new Ticket(counter++,ticket.getTicketType(),ticket.getPriorityType(),ticket.getEstimation(),ticket.getTittle(),ticket.getDescription());
                todoTicketList.add(ticketToAdd);
                ArrayList<Ticket> toadd = new ArrayList<>(todoTicketList);
                todoTicketsLiveData.setValue(toadd);
                return;
            }
        }
        for(Ticket t:this.inProgressTicketList){
            if(ticket.getId() == t.getId()){
                inProgressTicketList.remove(t);
                Ticket ticketToAdd = new Ticket(counter++,ticket.getTicketType(),ticket.getPriorityType(),ticket.getEstimation(),ticket.getTittle(),ticket.getDescription());
                inProgressTicketList.add(ticketToAdd);
                ArrayList<Ticket> toAdd = new ArrayList<>(inProgressTicketList);
                inProgressTicketsLiveData.setValue(toAdd);
                return;
            }
        }
        for(Ticket t:this.doneTicketList){
            if(ticket.getId() == t.getId()){
                doneTicketList.remove(t);
                Ticket ticketToAdd = new Ticket(counter++,ticket.getTicketType(),ticket.getPriorityType(),ticket.getEstimation(),ticket.getTittle(),ticket.getDescription());
                doneTicketList.add(ticketToAdd);
                ArrayList<Ticket> toAdd = new ArrayList<>(doneTicketList);
                doneTicketsLiveData.setValue(toAdd);
                return;
            }
        }

    }
}

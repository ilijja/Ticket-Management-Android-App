package com.example.ilija_dimitrijevic_rn9920.model;

public class Ticket {

    private int id;
    private TicketType ticketType;
    private PriorityType priorityType;
    private int estimation;
    private String tittle;
    private String description;
    private int loggedTime;

    public Ticket(int id,TicketType ticketType, PriorityType priorityType, int estimation, String tittle, String description) {
        this.id = id;
        this.ticketType = ticketType;
        this.priorityType = priorityType;
        this.estimation = estimation;
        this.tittle = tittle;
        this.description = description;
        this.loggedTime = 0;
    }

    public int getId() {
        return id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public int getEstimation() {
        return estimation;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public int getLoggedTime() {
        return loggedTime;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLoggedTime(int loggedTime) {
        this.loggedTime = loggedTime;
    }
}

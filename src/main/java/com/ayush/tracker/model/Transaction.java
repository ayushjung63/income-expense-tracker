package com.ayush.tracker.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String date;
    @NotNull(message = "Transcation Amount cannot be null")
    private double amount;
    @NotNull(message ="Transcation Amount cannot be null")
    private String description;
    @Enumerated(EnumType.STRING)
    @NotNull(message ="Transcation Type cannot be null")
    private TranscationType type;

    public enum TranscationType{
        EXPENSE,INCOME
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TranscationType getType() {
        return type;
    }

    public void setType(TranscationType type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}


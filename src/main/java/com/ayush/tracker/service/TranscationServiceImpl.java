package com.ayush.tracker.service;

import com.ayush.tracker.model.*;
import com.ayush.tracker.repo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.text.*;
import java.time.*;
import java.util.*;

@Service
public class TranscationServiceImpl implements  TranscationService{
    private TransactionRepo transactionRepo;
    @Autowired
    public void setTransactionRepo(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public List<Transaction> allTranscation() {
        return transactionRepo.findAll();
    }

    @Override
    public Transaction saveTranscation(Transaction transaction) throws ParseException {
        LocalDate localDate=LocalDate.now();
        transaction.setDate(localDate.toString());
        return transactionRepo.save(transaction);
    }

    @Override
    public Boolean deleteTranscation(int id) {
        try{transactionRepo.deleteById(id);
        return  true;
        }
        catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public List<Transaction> getTranscationByDate(Search search) {
        String from=search.getFrom();
        String to=search.getTo();
        System.out.println(this.transactionRepo.findByDateFromTo(from,to));
        return this.transactionRepo.findByDateFromTo(from,to);
    }

    @Override
    public List<Transaction> todayTranscation() {
        LocalDate localDate=LocalDate.now();
        String today=localDate.toString();
        return  this.transactionRepo.findByToday(today);
    }


}

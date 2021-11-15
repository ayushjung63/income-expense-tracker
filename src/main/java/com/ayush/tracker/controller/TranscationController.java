package com.ayush.tracker.controller;

import com.ayush.tracker.model.*;
import com.ayush.tracker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import javax.validation.*;
import java.util.*;
import java.util.stream.*;

@Controller
public class TranscationController {
    private TranscationService transcationService;
    @Autowired
    public void setTranscationService(TranscationServiceImpl transcationService) {
        this.transcationService = transcationService;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveTranscation(@Valid @ModelAttribute Transaction transaction, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return  "index";
        }else{
            try {
                Transaction transaction1 = transcationService.saveTranscation(transaction);
                return "redirect:/";
            } catch (Exception e) {
                System.out.println("Here");
                e.printStackTrace();
                return null;
            }
        }
    }

    private boolean checkTranscation(Transaction transaction) {
        return transaction.getDescription() != "" && transaction.getAmount() != 0
                && transaction.getType() != null && transaction.getDescription() != null;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public  ModelAndView allTranscation(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        List<Transaction> transactionList=transcationService.allTranscation();
        List<Transaction> incomeList = transactionList.stream()
                .filter(t->t.getType().equals(Transaction.TranscationType.INCOME))
                .collect(Collectors.toList());
        List<Transaction> expenseList = transactionList.stream()
                .filter(t->t.getType().equals(Transaction.TranscationType.EXPENSE))
                .collect(Collectors.toList());

            double totalIncome = incomeList.stream().collect(Collectors.summingDouble(t -> t.getAmount()));
            double totalExpense = expenseList.stream().collect(Collectors.summingDouble(t -> t.getAmount()));
            double gross = totalIncome - totalExpense;
            modelAndView.addObject("transcationList", transactionList);
            modelAndView.addObject("totalExpense", totalExpense);
            modelAndView.addObject("totalIncome", totalIncome);
            modelAndView.addObject("gross", gross);
            modelAndView.addObject("message","All Transcations");
        return  modelAndView;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  ModelAndView todayTranscation(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        List<Transaction> transactionList=transcationService.todayTranscation();
        List<Transaction> incomeList = transactionList.stream()
                .filter(t->t.getType().equals(Transaction.TranscationType.INCOME))
                .collect(Collectors.toList());
        List<Transaction> expenseList = transactionList.stream()
                .filter(t->t.getType().equals(Transaction.TranscationType.EXPENSE))
                .collect(Collectors.toList());

        double totalIncome = incomeList.stream().collect(Collectors.summingDouble(t -> t.getAmount()));
        double totalExpense = expenseList.stream().collect(Collectors.summingDouble(t -> t.getAmount()));
        double gross = totalIncome - totalExpense;
        modelAndView.addObject("transcationList", transactionList);
        modelAndView.addObject("totalExpense", totalExpense);
        modelAndView.addObject("totalIncome", totalIncome);
        modelAndView.addObject("gross", gross);
        modelAndView.addObject("message","Today's Transcations");
        return  modelAndView;
    }


    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ModelAndView searchTranscationByDate(@ModelAttribute Search search) throws Exception {
        if (search==null){
            throw new Exception("No proper date specified");
        }
        List<Transaction> transactionList = this.transcationService.getTranscationByDate(search);
        List<Transaction> incomeList = transactionList.stream()
                .filter(t->t.getType().equals(Transaction.TranscationType.INCOME))
                .collect(Collectors.toList());
        List<Transaction> expenseList = transactionList.stream()
                .filter(t->t.getType().equals(Transaction.TranscationType.EXPENSE))
                .collect(Collectors.toList());

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index.html");

        double totalIncome = incomeList.stream().collect(Collectors.summingDouble(t -> t.getAmount()));
        double totalExpense = expenseList.stream().collect(Collectors.summingDouble(t -> t.getAmount()));
        double gross = totalIncome - totalExpense;
        modelAndView.addObject("transcationList", transactionList);
        modelAndView.addObject("totalExpense", totalExpense);
        modelAndView.addObject("totalIncome", totalIncome);
        modelAndView.addObject("gross", gross);
        modelAndView.addObject("message","Transcation from "+search.getFrom()+" to "+search.getTo());
        return  modelAndView;
    }
}

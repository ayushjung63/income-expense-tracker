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

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  ModelAndView addPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        List<Transaction> transactionList=transcationService.allTranscation();
            List<Transaction> incomeList = transcationService.getIncome();
            List<Transaction> expenseList = transcationService.getExpense();
            double totalIncome = incomeList.stream().collect(Collectors.summingDouble(t -> t.getAmount()));
            double totalExpense = expenseList.stream().collect(Collectors.summingDouble(t -> t.getAmount()));
            double gross = totalIncome - totalExpense;
            modelAndView.addObject("transcationList", transactionList);
            modelAndView.addObject("totalExpense", totalExpense);
            modelAndView.addObject("totalIncome", totalIncome);
            modelAndView.addObject("gross", gross);
        return  modelAndView;
    }
}

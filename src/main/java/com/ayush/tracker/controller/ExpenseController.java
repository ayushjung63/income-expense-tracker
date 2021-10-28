package com.ayush.tracker.controller;

import com.ayush.tracker.model.*;
import com.ayush.tracker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
    private ExpenseService expenseService;
    @Autowired
    public void setExpenseService(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }

    @RequestMapping("/form")
    public ModelAndView addExpense(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addExpense");
        return modelAndView;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public  ModelAndView saveExpense(@ModelAttribute Expense expense){
        try {
            Expense expense1=expenseService.saveExpenses(expense);
            return  new ModelAndView("redirect:/");
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}

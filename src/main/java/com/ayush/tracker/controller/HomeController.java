package com.ayush.tracker.controller;

import com.ayush.tracker.model.*;
import com.ayush.tracker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;
import java.util.stream.*;

@Controller
public class HomeController {
    private IncomeService incomeService;
    private  ExpenseService expenseService;

    @Autowired
    public void setIncomeService(IncomeServiceImpl incomeService) {
        this.incomeService = incomeService;
    }
    @Autowired
    public void setExpenseService(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView homePage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        List<Income> incomeList=incomeService.allIncome();
        List<Expense> expenseList=expenseService.allExpenses();
        Double totalIncome=incomeList.stream()
                            .collect(Collectors.summingDouble(income->income.getAmount()));
        Double totalExpense=expenseList.stream()
                            .collect(Collectors.summingDouble(expense->expense.getAmount()));
        Double gross=totalIncome-totalExpense;
        modelAndView.addObject("incomeList",incomeList);
        modelAndView.addObject("expenseList",expenseList);
        modelAndView.addObject("totalIncome",totalIncome);
        modelAndView.addObject("totalExpense",totalExpense);
        modelAndView.addObject("gross",gross);
        return modelAndView;
    }
}

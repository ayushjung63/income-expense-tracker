package com.ayush.tracker.service;

import com.ayush.tracker.model.*;
import com.ayush.tracker.repo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ExpenseServiceImpl implements  ExpenseService {

    private ExpenseRepo expenseRepo;

    @Autowired
    public void setExpenseRepo(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Override
    public List<Expense> allExpenses() {
        return expenseRepo.findAll();
    }

    @Override
    public Expense saveExpenses(Expense expense) {
        return expenseRepo.save(expense);
    }

    @Override
    public Boolean deleteExpenses(int id) {
       try{
            expenseRepo.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
}

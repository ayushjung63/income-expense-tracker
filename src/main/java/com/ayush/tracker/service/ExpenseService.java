package com.ayush.tracker.service;

import com.ayush.tracker.model.*;

import java.util.*;

public interface ExpenseService {
    List<Expense> allExpenses();
    Expense saveExpenses(Expense expense);
    Boolean deleteExpenses(int id);
}

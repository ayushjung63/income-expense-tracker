package com.ayush.tracker.service;

import com.ayush.tracker.model.*;

import java.util.*;

public interface IncomeService {
    List<Income> allIncome();
    Income saveIncome(Income income);
    Boolean deleteIncome(int id);
}

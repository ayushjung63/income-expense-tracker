package com.ayush.tracker.service;

import com.ayush.tracker.model.*;
import com.ayush.tracker.repo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class IncomeServiceImpl implements IncomeService{

    private IncomeRepo incomeRepo;

    @Autowired
    public void setIncomeRepo(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    @Override
    public List<Income> allIncome() {
        return incomeRepo.findAll();
    }

    @Override
    public Income saveIncome(Income income) {
        return incomeRepo.save(income);
    }

    @Override
    public Boolean deleteIncome(int id) {
        try{
            incomeRepo.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }

    }
}

package com.ayush.tracker.controller;

import com.ayush.tracker.model.*;
import com.ayush.tracker.service.*;
import org.dom4j.rule.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
@RequestMapping("/income")
public class IncomeController {
    private IncomeService incomeService;

    @Autowired
    public void setIncomeService(IncomeServiceImpl incomeServiceImpl) {
        this.incomeService = incomeServiceImpl;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView allIncome() {
        ModelAndView modelAndView = new ModelAndView();
        List<Income> incomeList = incomeService.allIncome();
        modelAndView.setViewName("Income");
        modelAndView.addObject("incomeList", incomeList);
        return  modelAndView;
    }

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public  ModelAndView saveIncomePage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Income-save");
        return  modelAndView;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ModelAndView saveIncome(@ModelAttribute Income income){
        try {
            Income savedIncome = incomeService.saveIncome(income);
            return  new ModelAndView("redirect:/");
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}

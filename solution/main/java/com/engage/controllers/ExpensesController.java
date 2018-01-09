package com.engage.controllers;

import com.engage.model.Expense;
import com.engage.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/app/expenses")
public class ExpensesController {

    @Autowired
    ExpensesRepository expensesRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    String retrieveExpenses() {
        List<Expense> expenses = (List<Expense>) this.expensesRepository.findAll();
        return "{}";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    String persistExpense(@RequestBody Expense expense) {
        Expense persistedExpense = this.expensesRepository.save(expense);
        return "{}";
    }

}

package com.engage.controllers;

import com.engage.model.Expense;
import com.engage.services.DefaultExpenseService;
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
    DefaultExpenseService defaultExpenseService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Expense> retrieveExpenses() {
        return this.defaultExpenseService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Expense persistExpense(@RequestBody Expense expense) {
        Expense save = this.defaultExpenseService.save(expense);
        return save;
    }

}

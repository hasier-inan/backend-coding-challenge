package com.engage.controllers;

import com.engage.model.Expense;
import com.engage.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hasiermetal on 08/01/2018.
 * Main controller to manage expenses in the application. Uses the expense service to retrieve and create new expenses.
 */
@Controller
@RequestMapping("/app/expenses")
public class ExpensesController {

    @Autowired
    ExpenseService defaultExpenseService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Expense> retrieveExpenses() {
        return this.defaultExpenseService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Expense persistExpense(@RequestBody Expense expense) {
        return this.defaultExpenseService.save(expense);
    }

}

package com.engage.services;

import com.engage.model.Expense;

import java.util.List;

/**
 * Created by hasiermetal on 09/01/2018.
 * Defines methods that will be implemented in the service bean.
 */
public interface ExpenseService {

    List<Expense> findAll();

    Expense save(Expense expense);
}

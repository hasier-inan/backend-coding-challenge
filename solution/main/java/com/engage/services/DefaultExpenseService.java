package com.engage.services;

import com.engage.model.Expense;
import com.engage.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hasiermetal on 09/01/2018.
 */
@Service
public class DefaultExpenseService implements ExpenseService {

    @Autowired
    ExpensesRepository expensesRepository;

    @Override
    public List<Expense> findAll() {
        return (List<Expense>) this.expensesRepository.findAll();
    }

    @Override
    public Expense save(Expense expense) {
        return this.expensesRepository.save(expense);
    }

}

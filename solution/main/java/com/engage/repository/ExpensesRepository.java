package com.engage.repository;

import com.engage.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hasiermetal on 08/01/2018.
 */
@Repository
public interface ExpensesRepository extends CrudRepository<Expense, Long> {
}

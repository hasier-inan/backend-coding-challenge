package com.engage.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hasiermetal on 09/01/2018.
 */
public class ExpenseBuilder {

    protected Long id;
    protected Date date;
    protected BigDecimal amount;
    protected String reason;

    public static ExpenseBuilder anExpense() {
        return new ExpenseBuilder();
    }

    public ExpenseBuilder() {
        super();
    }

    public ExpenseBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ExpenseBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public ExpenseBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public ExpenseBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Expense build() {
        Expense theUser = new Expense();
        theUser.setId(this.id);
        theUser.setAmount(this.amount);
        theUser.setDate(this.date);
        theUser.setReason(this.reason);
        return theUser;
    }
}

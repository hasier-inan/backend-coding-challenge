package com.engage.controllers;


import com.engage.model.Expense;
import com.engage.model.ExpenseBuilder;
import com.engage.repository.ExpensesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import static com.engage.utils.JsonMapper.getJsonFromMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by hasiermetal on 08/01/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExpensesControllerTest {

    private static final String expensesUrl = "/app/expenses";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpensesRepository expensesRepository;

    @Test
    public void retrieveExpenses() throws Exception {
        when(this.expensesRepository.findAll()).thenReturn(null);
        mockMvc.perform(get(expensesUrl))
                .andExpect(content().string(containsString("{}")));
        verify(this.expensesRepository, times(1)).findAll();
    }

    @Test
    public void persistExpense() throws Exception {
        ArgumentCaptor<Expense> expenseArgumentCaptor = ArgumentCaptor.forClass(Expense.class);
        when(this.expensesRepository.save(any(Expense.class))).thenReturn(null);

        mockMvc.perform(post(expensesUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createExpenseRequest()))
                .andExpect(content().string(containsString("{}")));

        verify(this.expensesRepository, times(1)).save(expenseArgumentCaptor.capture());
        assertThat("Expected expense to have been persisted", expenseArgumentCaptor.getValue().getId(), is(12L));
    }

    private String createExpenseRequest() throws IOException {
        return getJsonFromMap(createExpenseSample());
    }

    private Expense createExpenseSample() {
        return ExpenseBuilder.anExpense()
                .withId(12L)
                .withAmount(new BigDecimal(66.6).setScale(2, RoundingMode.CEILING))
                .withDate(new Date())
                .withReason("Something pretty")
                .build();
    }

}
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * Verifies the controller endpoint mappings are correctly defined.
 * Mocks the DAO to avoid writing to the database, but checks whether the expense has been called to be saved/retrieved.
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
    public void testExpensesAreRetrieved() throws Exception {
        Long sampleExpenseId = 123456789L;
        when(this.expensesRepository.findAll()).thenReturn(createMultipleExpenses(sampleExpenseId));
        mockMvc.perform(get(expensesUrl))
                .andExpect(content().string(containsString(sampleExpenseId.toString())));
        verify(this.expensesRepository, times(1)).findAll();
    }

    @Test
    public void testExpensesArePersisted() throws Exception {
        Long sampleExpenseId = 987654321L;
        ArgumentCaptor<Expense> expenseArgumentCaptor = ArgumentCaptor.forClass(Expense.class);
        when(this.expensesRepository.save(any(Expense.class))).thenReturn(createExpenseSample(sampleExpenseId));

        mockMvc.perform(post(expensesUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createExpenseRequest(sampleExpenseId)))
                .andExpect(content().string(containsString(sampleExpenseId.toString())));

        verify(this.expensesRepository, times(1)).save(expenseArgumentCaptor.capture());
        assertThat("Expected expense to have been persisted", expenseArgumentCaptor.getValue().getId(), is(sampleExpenseId));
    }

    private String createExpenseRequest(Long sampleId) throws IOException {
        return getJsonFromMap(createExpenseSample(sampleId));
    }

    private List<Expense> createMultipleExpenses(Long id) {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(createExpenseSample(id));
        return expenses;
    }

    private Expense createExpenseSample(Long id) {
        return ExpenseBuilder.anExpense()
                .withId(id)
                .withAmount(new BigDecimal(66.6).setScale(2, RoundingMode.CEILING))
                .withDate(new Date())
                .withReason("Something pretty")
                .build();
    }

}
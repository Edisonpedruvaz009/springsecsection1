package com.edison.springsecsection1.controller;

import com.edison.springsecsection1.model.AccountTransactions;
import com.edison.springsecsection1.repository.AccountTransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BalanceControllerTest {

    @Mock
    private AccountTransactionsRepository accountTransactionsRepository;

    @InjectMocks
    private BalanceController balanceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBalanceDetails_withValidId() {
        long customerId = 1L;
        List<AccountTransactions> mockTransactions = Arrays.asList(
                new AccountTransactions(/* initialize with test data */),
                new AccountTransactions(/* initialize with test data */)
        );

        when(accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(customerId))
                .thenReturn(mockTransactions);

        List<AccountTransactions> result = balanceController.getBalanceDetails(customerId);

        assertEquals(mockTransactions, result);
    }

    @Test
    public void testGetBalanceDetails_withInvalidId() {
        long customerId = 2L;

        when(accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(customerId))
                .thenReturn(null);

        List<AccountTransactions> result = balanceController.getBalanceDetails(customerId);

        assertEquals(null, result);
    }
}
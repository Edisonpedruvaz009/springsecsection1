package com.edison.springsecsection1.controller;

import com.edison.springsecsection1.model.Accounts;
import com.edison.springsecsection1.repository.AccountsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccountDetails_AccountExists() {
        long customerId = 1L;
        Accounts mockAccount = new Accounts();
        mockAccount.setCustomerId(customerId);

        when(accountsRepository.findByCustomerId(customerId)).thenReturn(mockAccount);

        Accounts result = accountController.getAccountDetails(customerId);

        assertEquals(mockAccount, result);
    }

    @Test
    public void testGetAccountDetails_AccountDoesNotExist() {
        long customerId = 1L;

        when(accountsRepository.findByCustomerId(customerId)).thenReturn(null);

        Accounts result = accountController.getAccountDetails(customerId);

        assertNull(result);
    }
}
package com.edison.springsecsection1.config;

import com.edison.springsecsection1.model.Authority;
import com.edison.springsecsection1.model.Customer;
import com.edison.springsecsection1.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EazyBankUserDetailsServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private EazyBankUserDetailsService eazyBankUserDetailsService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPwd("password");
        Authority authority = new Authority();
        authority.setName("ROLE_USER");
        customer.setAuthorities(Collections.singleton(authority));
    }

    @Test
    public void testLoadUserByUsername_Success() {
        when(customerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(customer));

        UserDetails userDetails = eazyBankUserDetailsService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(customerRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            eazyBankUserDetailsService.loadUserByUsername("unknown@example.com");
        });
    }
}
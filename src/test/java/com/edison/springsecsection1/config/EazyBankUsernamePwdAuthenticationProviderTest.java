package com.edison.springsecsection1.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EazyBankUsernamePwdAuthenticationProviderTest {

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private EazyBankUsernamePwdAuthenticationProvider authenticationProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticate_Success() {
        String username = "testUser";
        String password = "testPassword";
        UserDetails userDetails = mock(UserDetails.class);

        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("encodedPassword");

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication result = authenticationProvider.authenticate(authentication);

        assertNotNull(result);
        assertEquals(username, result.getName());
        assertEquals(password, result.getCredentials());
        assertEquals(userDetails.getAuthorities(), result.getAuthorities());
    }



    @Test
    void testSupports() {
        assertTrue(authenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
    }
}
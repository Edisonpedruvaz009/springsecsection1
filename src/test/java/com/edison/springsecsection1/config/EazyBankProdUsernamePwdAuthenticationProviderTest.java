package com.edison.springsecsection1.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EazyBankProdUsernamePwdAuthenticationProviderTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EazyBankProdUsernamePwdAuthenticationProvider authenticationProvider;

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
        when(userDetails.getPassword()).thenReturn(password);
        when(passwordEncoder.matches(password, userDetails.getPassword())).thenReturn(true);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication result = authenticationProvider.authenticate(authentication);

        assertNotNull(result);
        assertEquals(username, result.getName());
        assertEquals(password, result.getCredentials());
        assertEquals(userDetails.getAuthorities(), result.getAuthorities());
    }

    @Test
    void testAuthenticate_Failure() {
        String username = "testUser";
        String password = "testPassword";
        UserDetails userDetails = mock(UserDetails.class);

        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("wrongPassword");
        when(passwordEncoder.matches(password, userDetails.getPassword())).thenReturn(false);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(authentication);
        });
    }

    @Test
    void testSupports() {
        assertTrue(authenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
    }
}
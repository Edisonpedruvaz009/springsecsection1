package com.edison.springsecsection1.config;

import com.edison.springsecsection1.exceptionhandling.CustomAccessDeniedHandler;
import com.edison.springsecsection1.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.edison.springsecsection1.filter.CsrfCookieFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(ProjectSecurityConfig.class)
public class ProjectSecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Mock
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Mock
    private CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    @Mock
    private CsrfCookieFilter csrfCookieFilter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CompromisedPasswordChecker compromisedPasswordChecker;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void contextLoads() {
        // This test can be empty, it just ensures the context loads successfully
    }

    @Test
    public void testPublicEndpoints() throws Exception {
        mockMvc.perform(get("/notices")).andExpect(status().isOk());
//        mockMvc.perform(get("/contact")).andExpect(status().isOk());
//        mockMvc.perform(get("/error")).andExpect(status().isOk());
//        mockMvc.perform(get("/register")).andExpect(status().isOk());
//        mockMvc.perform(get("/invalidSession")).andExpect(status().isOk());
    }





    @Test
    @WithMockUser(roles = "USER")
    public void testMyAccountEndpoint() throws Exception {
        mockMvc.perform(get("/myAccount").param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void testMyBalanceEndpoint() throws Exception {
        mockMvc.perform(get("/myBalance").param("id", "1"))
                .andExpect(status().isOk());
    }


//    @Test
//    @WithMockUser(authorities = "USER")
//    public void testMyLoansEndpoint() throws Exception {
//        mockMvc.perform(get("/myLoans").param("id", "1"))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(roles = "USER")
    public void testMyLoansEndpoint() throws Exception {
        mockMvc.perform(get("/myLoans").param("id", "1"))
                .andExpect(status().isOk());
    }



    @Test
    @WithMockUser(roles = "USER")
    public void testMyCardsEndpoint() throws Exception {
        mockMvc.perform(get("/myCards").param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testUserEndpoint() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk());
    }

//    @Test
//    public void testFormLogin() throws Exception {
//        mockMvc.perform(formLogin().user("user").password("password")).andExpect(status().isOk());
//    }




}
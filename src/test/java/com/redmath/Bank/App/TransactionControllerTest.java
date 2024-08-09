package com.redmath.Bank.App;
import com.redmath.Bank.App.Transaction.Transaction;
import com.redmath.Bank.App.User.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class TransactionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private Transaction transaction;
//
//    @BeforeEach
//    void setUp() {
//        User sender = new User();
//        sender.setId(3L);
//
//        User receiver = new User();
//        receiver.setAccountNumber("1234567890");
//        receiver.setId(1L);
//        transaction = new Transaction();
//        transaction.setSender(sender);
//        transaction.setReceiver(receiver);
//        transaction.setAmount(100.0);
//        transaction.setDate(LocalDateTime.now());
//    }
//
//    @Order(1)
//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    void shouldReturnAllTransactions() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transactions/all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Order(2)
//    @Test
//    @WithMockUser(roles = {"USER"})
//    void shouldReturnForbiddenWhenNonAdminAccessesGetAll() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transactions/all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isForbidden());
//    }
//    @Order(3)
//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
//        User sender = new User();
//        sender.setId(3L);
//
//        User receiver = new User();
//        receiver.setAccountNumber("7890");
//        receiver.setId(null);
//
//        // Create a transaction
//        Transaction transaction = new Transaction();
//        transaction.setSender(sender);
//        transaction.setReceiver(receiver);
//        transaction.setAmount(100.0);
//        transaction.setDate(LocalDateTime.now());
//
//        // Perform the request
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(transaction)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.content().string("User not found"));
//    }
//
//
//    @Order(4)
//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    void shouldCreateTransaction() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(transaction)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.sender.id", Matchers.is(3)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.receiver.id", Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(100.0)));
//    }
//
//
//
//    @Order(5)
//    @Test
//    @WithMockUser
//    void shouldReturnTransactionsForUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transactions/3")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThanOrEqualTo(1))));
//    }
}



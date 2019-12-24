package com.backbase.test.controller;

import com.backbase.test.domain.Transaction;
import com.backbase.test.service.OpenBankTransactionUri;
import com.backbase.test.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    public MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .build();
    }

    @Test
    public void welcomeTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome user"));
    }

    @Test
    public void retrieveTransactionsTest() throws Exception {
        Mockito.when(transactionService.retrieveFilteredTransactions(Mockito.any(OpenBankTransactionUri.class), Mockito.any()))
                .thenReturn(Collections.singletonList(Transaction.builder().build()));

        String expectedContent = "[{\"id\":null,\"accountId\":null,\"counterpartyAccount\":null,\"counterpartyName\":null,\"counterPartyLogoPath\":null,\"instructedAmount\":null,\"instructedCurrency\":null,\"transactionAmount\":null,\"transactionCurrency\":null,\"transactionType\":null,\"description\":null}]";

        mockMvc
                .perform(MockMvcRequestBuilders.get("/banks/{BANK_ID}/accounts/{ACCOUNT_ID}/transactions", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void retrieveTransactionsFilterTest() throws Exception {
        Mockito.when(transactionService.retrieveFilteredTransactions(Mockito.any(OpenBankTransactionUri.class), Mockito.anyString()))
                .thenReturn(Collections.singletonList(Transaction.builder().build()));

        String expectedContent = "[{\"id\":null,\"accountId\":null,\"counterpartyAccount\":null,\"counterpartyName\":null,\"counterPartyLogoPath\":null,\"instructedAmount\":null,\"instructedCurrency\":null,\"transactionAmount\":null,\"transactionCurrency\":null,\"transactionType\":null,\"description\":null}]";

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/banks/{BANK_ID}/accounts/{ACCOUNT_ID}/transactions", 1, 1)
                        .param("transactionType", "type"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void countFilteredTransactionsByTypeTest() throws Exception {

        Mockito.when(transactionService.totalCountTransactionsByType(Mockito.any(OpenBankTransactionUri.class), Mockito.anyString()))
                .thenReturn(12L);

        String expectedContent = "12";

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/banks/{BANK_ID}/accounts/{ACCOUNT_ID}/transactions/count", 2, 2)
                                .param("transactionType", "type"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));

    }

}
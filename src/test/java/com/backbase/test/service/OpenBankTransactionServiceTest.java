package com.backbase.test.service;

import com.backbase.test.domain.OpenBankTransaction;
import com.backbase.test.domain.OpenBankTransactions;
import com.backbase.test.service.impl.OpenBankTransactionServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OpenBankTransactionServiceTest extends TestCase {

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testIngestTransactions() {
        OpenBankTransactionUri transactionUri = OpenBankTransactionUri.builder()
                .bankId("11")
                .accountId("12")
                .build();

        OpenBankTransactionServiceImpl openBankTransactionService = new OpenBankTransactionServiceImpl(
                restTemplate, "https://apisandbox.openbankproject.com/obp/v1.2.1");

        OpenBankTransactions bankTransactions = new OpenBankTransactions();
        List<OpenBankTransaction> transactions = bankTransactions.getTransactions();
        transactions.add(new OpenBankTransaction());
        transactions.add(new OpenBankTransaction());
        bankTransactions.setTransactions(transactions);


        URI expectedUri = URI.create("https://apisandbox.openbankproject.com/obp/v1.2.1/banks/11/accounts/12/public/transactions");
        Mockito.when(restTemplate.getForObject(expectedUri, OpenBankTransactions.class)).thenReturn(bankTransactions);

        List<OpenBankTransaction> openBankTransactions = openBankTransactionService.ingestTransactions(transactionUri);

        TestCase.assertEquals(2, openBankTransactions.size());
    }
}
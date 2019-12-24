package com.backbase.test.service;

import com.backbase.test.domain.OpenBankTransaction;
import com.backbase.test.domain.Transaction;
import com.backbase.test.service.impl.TransactionServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest extends TestCase {

    @Mock
    private OpenBankTransactionService openBankTransactionService;

    @Test
    public void testRetrieveFilteredTransactionsTest() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl(openBankTransactionService);
        OpenBankTransactionUri bankTransactionUri = OpenBankTransactionUri.builder()
                .accountId("1")
                .bankId("2")
                .build();

        OpenBankTransaction openBankTransaction1 = new OpenBankTransaction();
        OpenBankTransaction openBankTransaction2 = new OpenBankTransaction();
        OpenBankTransaction openBankTransaction3 = new OpenBankTransaction();

        OpenBankTransaction.Details details2 = new OpenBankTransaction.Details();
        details2.setType("type");
        openBankTransaction2.setDetails(details2);
        OpenBankTransaction.OtherAccount other_account2 = new OpenBankTransaction.OtherAccount();
        OpenBankTransaction.OtherAccount.Holder holder2 = new OpenBankTransaction.OtherAccount.Holder();
        OpenBankTransaction.OtherAccount.Metadata metadata2 = new OpenBankTransaction.OtherAccount.Metadata();
        holder2.setName("Holder2");
        other_account2.setHolder(holder2);
        other_account2.setMetadata(metadata2);
        openBankTransaction2.setOther_account(other_account2);

        OpenBankTransaction.Details details3 = new OpenBankTransaction.Details();
        details3.setType("type2");
        openBankTransaction3.setDetails(details3);

        Mockito.when(openBankTransactionService.ingestTransactions(bankTransactionUri))
                .thenReturn(Arrays.asList(openBankTransaction1, openBankTransaction2));

        List<Transaction> transactions = transactionService.retrieveFilteredTransactions(bankTransactionUri, "type");

        TestCase.assertEquals(1, transactions.size());

    }


    @Test
    public void testTotalCountTransactionsByTypeTest() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl(openBankTransactionService);
        OpenBankTransactionUri bankTransactionUri = OpenBankTransactionUri.builder()
                .accountId("1")
                .bankId("2")
                .build();

        OpenBankTransaction openBankTransaction1 = new OpenBankTransaction();
        OpenBankTransaction openBankTransaction2 = new OpenBankTransaction();

        OpenBankTransaction.Details details = new OpenBankTransaction.Details();
        details.setType("type");
        openBankTransaction2.setDetails(details);

        Mockito.when(openBankTransactionService.ingestTransactions(bankTransactionUri))
                .thenReturn(Arrays.asList(openBankTransaction1, openBankTransaction2));

        long count = transactionService.totalCountTransactionsByType(bankTransactionUri, null);

        TestCase.assertEquals(2, count);
    }

}
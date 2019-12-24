package com.backbase.test.service;

import com.backbase.test.domain.OpenBankTransaction;
import com.backbase.test.domain.Transaction;
import org.springframework.lang.Nullable;

import java.util.List;

public interface TransactionService {

    /**
     * Retrieve and prepare transactions data to the expected format {@link Transaction}
     * @param bankTransactionUri {@link OpenBankTransaction}
     * @param transactionType implement filtering value
     * @return filtered transaction
     */
    List<Transaction> retrieveFilteredTransactions(OpenBankTransactionUri bankTransactionUri, @Nullable String transactionType);

    /**
     * Calculate number of filtered entries
     * @param bankTransactionUri {@link OpenBankTransaction}
     * @param transactionType implement filtering value
     * @return filtered transaction
     */
    long totalCountTransactionsByType(OpenBankTransactionUri bankTransactionUri, @Nullable String transactionType);

}

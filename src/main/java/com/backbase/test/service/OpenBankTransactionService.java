package com.backbase.test.service;

import com.backbase.test.domain.OpenBankTransaction;

import java.util.List;

public interface OpenBankTransactionService {

    /**
     * Retrieve data from external resources
     * @param bankTransactionUri URI location composer
     * @return raw OpenBank transaction data {@link OpenBankTransaction}
     */
    List<OpenBankTransaction> ingestTransactions(OpenBankTransactionUri bankTransactionUri);

}

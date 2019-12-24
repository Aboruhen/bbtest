package com.backbase.test.domain;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OpenBank transaction response entity
 */
@Setter
public class OpenBankTransactions {

    private List<OpenBankTransaction> transactions;

    public List<OpenBankTransaction> getTransactions() {
        if (Objects.isNull(this.transactions)) {
            transactions = new ArrayList<>();
        }
        return transactions;
    }
}

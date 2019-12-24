package com.backbase.test.service;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URI;

@RequiredArgsConstructor
@Builder
public class OpenBankTransactionUri {

    private static final String TRANSACTION_URI = "banks/BANK_ID/accounts/ACCOUNT_ID/VIEW_ID/transactions";

    private final String bankId;
    private final String accountId;

    public URI expandUri() {
        return URI.create("banks/")
                .resolve(bankId + "/")
                .resolve("accounts/")
                .resolve(accountId + "/")
                .resolve("public/")
                .resolve("transactions");
    }

}

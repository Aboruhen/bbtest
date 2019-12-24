package com.backbase.test.service;

import junit.framework.TestCase;
import org.junit.Test;

import java.net.URI;

public class OpenBankTransactionUriTest {

    @Test
    public void expandUri() {
        OpenBankTransactionUri bankTransactionUri = OpenBankTransactionUri.builder()
                .accountId("accountID")
                .bankId("bankID")
                .build();
        URI uri = bankTransactionUri.expandUri();
        TestCase.assertEquals("banks/bankID/accounts/accountID/public/transactions", uri.toString());
    }
}
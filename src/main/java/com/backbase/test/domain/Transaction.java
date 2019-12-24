package com.backbase.test.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Expected DTO for transaction response
 */
@Getter
@Builder
public class Transaction {

    private final String id;
    private final String accountId;
    private final String counterpartyAccount;
    private final String counterpartyName;
    private final String counterPartyLogoPath;
    private final String instructedAmount;
    private final String instructedCurrency;
    private final String transactionAmount;
    private final String transactionCurrency;
    private final String transactionType;
    private final String description;

}

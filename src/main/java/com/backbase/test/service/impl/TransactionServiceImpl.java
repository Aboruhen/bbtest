package com.backbase.test.service.impl;

import com.backbase.test.domain.OpenBankTransaction;
import com.backbase.test.domain.Transaction;
import com.backbase.test.service.OpenBankTransactionService;
import com.backbase.test.service.OpenBankTransactionUri;
import com.backbase.test.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final OpenBankTransactionService openBankTransactionServiceImpl;

    /**
     * Retrieve and prepare transactions data to the expected format {@link Transaction}
     * @param bankTransactionUri {@link OpenBankTransaction}
     * @param transactionType implement filtering value
     * @return filtered transaction
     */
    public List<Transaction> retrieveFilteredTransactions(OpenBankTransactionUri bankTransactionUri, @Nullable String transactionType) {
        return openBankTransactionServiceImpl.ingestTransactions(bankTransactionUri).stream()
                .filter(transactionTypeFilter(transactionType))
                .filter(Objects::nonNull)
                .map(TransactionServiceImpl::transactionTransformer)
                .collect(Collectors.toList());
    }

    /**
     * Calculate number of filtered entries
     * @param bankTransactionUri {@link OpenBankTransaction}
     * @param transactionType implement filtering value
     * @return filtered transaction
     */
    @Override
    public long totalCountTransactionsByType(OpenBankTransactionUri bankTransactionUri, @Nullable String transactionType) {
        return retrieveFilteredTransactions(bankTransactionUri, transactionType).size();
    }

    private static Transaction transactionTransformer(OpenBankTransaction obt) {
        return Transaction.builder()
                .id(obt.getId())
                .accountId(obt.getThisAccountId())
                .counterpartyAccount(obt.getOtherAccountNumber())
                .counterpartyName(obt.getOtherAccountHolderName())
                .counterPartyLogoPath(obt.getOtherAccountMetadataImageUrl())
                .instructedAmount(obt.getDetailsValueAmount())
                .instructedCurrency(obt.getDetailsValueCurrency())
                .transactionAmount(obt.getDetailsValueAmount())
                .transactionCurrency(obt.getDetailsValueCurrency())
                .transactionType(obt.getDetailsType())
                .description(obt.getDetailsDescription())
                .build();
    }

    private static Predicate<OpenBankTransaction> transactionTypeFilter(@Nullable String transactionType) {
        return Objects.isNull(transactionType)
                ? obt -> true
                : obt -> transactionType.equals(obt.getDetailsType());
    }

}

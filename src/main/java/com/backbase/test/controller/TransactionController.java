package com.backbase.test.controller;

import com.backbase.test.domain.Transaction;
import com.backbase.test.service.OpenBankTransactionUri;
import com.backbase.test.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "Transaction Open Bank Transformer")
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation(value = "Greeting endpoint", notes = "Used for test")
    @GetMapping("/")
    @SuppressWarnings("unused")
    public String welcome() {
        return "Welcome user";
    }

    /**
     * Retrieve transactions from Open Bank and transform it to {@link Transaction}
     *
     * @param bankId          open bank id
     * @param accountId       open bank id
     * @param transactionType can de used for filtering retrieved data
     * @return transformed entries
     */
    @SuppressWarnings("unused")
    @ApiOperation(value = "Retrieve transactions from Open Bank and transform it", notes = "Used for test")
    @GetMapping(value = "banks/{BANK_ID}/accounts/{ACCOUNT_ID}/transactions")
    public List<Transaction> retrieveTransactions(
            @PathVariable("BANK_ID") String bankId,
            @PathVariable("ACCOUNT_ID") String accountId,
            @RequestParam(value = "transactionType", required = false) String transactionType) {
        OpenBankTransactionUri bankTransactionUri = OpenBankTransactionUri.builder()
                .accountId(accountId)
                .bankId(bankId)
                .build();
        return transactionService.retrieveFilteredTransactions(bankTransactionUri, transactionType);
    }

    /**
     * Calculate number of transactions from Open Bank
     *
     * @param bankId          open bank id
     * @param accountId       open bank id
     * @param transactionType can de used for filtering retrieved data
     * @return count of transformed entries
     */
    @SuppressWarnings("unused")
    @GetMapping(value = "banks/{BANK_ID}/accounts/{ACCOUNT_ID}/transactions/count")
    public long countFilteredTransactionsByType(
            @PathVariable("BANK_ID") String bankId,
            @PathVariable("ACCOUNT_ID") String accountId,
            @RequestParam(value = "transactionType", required = false) String transactionType) {
        OpenBankTransactionUri bankTransactionUri = OpenBankTransactionUri.builder()
                .accountId(accountId)
                .bankId(bankId)
                .build();
        return transactionService.totalCountTransactionsByType(bankTransactionUri, transactionType);
    }

}

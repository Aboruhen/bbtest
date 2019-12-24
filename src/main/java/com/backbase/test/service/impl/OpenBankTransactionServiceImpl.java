package com.backbase.test.service.impl;

import com.backbase.test.domain.OpenBankTransaction;
import com.backbase.test.domain.OpenBankTransactions;
import com.backbase.test.service.OpenBankTransactionService;
import com.backbase.test.service.OpenBankTransactionUri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Implements transaction data ingestion from Open Bank API
 */
@Slf4j
@PropertySource("classpath:application.properties")
@Service
public class OpenBankTransactionServiceImpl implements OpenBankTransactionService {

    private final String openBankBaseUrl;
    private final RestTemplate restTemplate;

    public OpenBankTransactionServiceImpl(
            RestTemplate restTemplate,
            @Value("${OPEN_BANK_BASE_URL}") String openBankBaseUrl) {
        this.restTemplate = restTemplate;
        this.openBankBaseUrl = openBankBaseUrl;
    }

    public List<OpenBankTransaction> ingestTransactions(OpenBankTransactionUri bankTransactionUri) {
        URI uri = URI.create(openBankBaseUrl + "/").resolve(bankTransactionUri.expandUri());
        log.debug("uri: {}", uri);

        OpenBankTransactions openBankTransactions = restTemplate.getForObject(uri, OpenBankTransactions.class);

        if (Objects.isNull(openBankTransactions)) {
            return Collections.emptyList();
        }
        return Objects.isNull(openBankTransactions.getTransactions()) ? Collections.emptyList() : openBankTransactions.getTransactions();
    }

}

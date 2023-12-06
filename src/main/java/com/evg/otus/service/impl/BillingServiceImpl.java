package com.evg.otus.service.impl;

import com.evg.otus.dto.billing.response.BillingAccountResponse;
import com.evg.otus.service.BillingService;
import com.evg.otus.service.client.BillingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "BILLING_SERVICE")
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillingClient billingClient;

    @Override
    public BillingAccountResponse createAccount(Long userId) {
        return billingClient.createAccount(userId);
    }
}

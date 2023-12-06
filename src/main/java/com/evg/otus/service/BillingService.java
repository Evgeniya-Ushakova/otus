package com.evg.otus.service;

import com.evg.otus.dto.billing.response.BillingAccountResponse;

public interface BillingService {

    BillingAccountResponse createAccount(Long userId);

}

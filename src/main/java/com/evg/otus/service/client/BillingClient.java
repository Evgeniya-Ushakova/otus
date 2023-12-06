package com.evg.otus.service.client;

import com.evg.otus.dto.billing.response.BillingAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "billing", url = "${billing.server}", path = "/billing")
public interface BillingClient {

    @PostMapping("/account/create/{userId}")
    BillingAccountResponse createAccount(@PathVariable("userId") Long userId);

}

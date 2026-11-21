package org.kamal.msbilling.client;

import org.kamal.msbilling.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-customer", url = "http://localhost:8081")
public interface CustomerClient {

    @GetMapping("/api/customers/{id}")
    Customer getCustomerById(@PathVariable("id") Long id);
}

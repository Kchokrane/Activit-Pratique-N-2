package org.kamal.msbilling.controller;

import org.kamal.msbilling.model.Invoice;
import org.kamal.msbilling.service.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(billingService.getAllInvoices());
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(
            @RequestParam Long customerId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        Invoice invoice = billingService.createInvoice(customerId, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }
}

package org.kamal.msbilling.service;

import org.kamal.msbilling.client.CustomerClient;
import org.kamal.msbilling.client.ProductClient;
import org.kamal.msbilling.model.Customer;
import org.kamal.msbilling.model.Invoice;
import org.kamal.msbilling.model.Product;
import org.kamal.msbilling.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public BillingService(InvoiceRepository invoiceRepository, CustomerClient customerClient, ProductClient productClient) {
        this.invoiceRepository = invoiceRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice createInvoice(Long customerId, Long productId, Integer quantity) {
        // Récupérer le client via Feign
        Customer customer = customerClient.getCustomerById(customerId);

        // Récupérer le produit via Feign
        Product product = productClient.getProductById(productId);

        // Créer la facture
        Invoice invoice = new Invoice();
        invoice.setCustomerId(String.valueOf(customer.getId()));
        invoice.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        invoice.setCustomerEmail(customer.getEmail());
        invoice.setCustomerAddress(customer.getAddress());

        invoice.setProductId(String.valueOf(product.getId()));
        invoice.setProductName(product.getName());
        invoice.setPrice(product.getPrice());
        invoice.setQuantity(quantity);
        invoice.setTotalAmount(product.getPrice() * quantity);

        return invoiceRepository.save(invoice);
    }
}

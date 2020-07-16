package com.paduris.git.create.quote.service;

import com.paduris.git.create.quote.exception.CustomerNotFoundException;
import com.paduris.git.create.quote.model.Customer;
import com.paduris.git.create.quote.repository.CustomerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    @NonNull
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public Optional<Customer> findById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        return customerRepository.findById(customerId).map(customer -> {
            customerRepository.delete(customer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new CustomerNotFoundException("Customer Id" + customerId));
    }

}
